from lxml import html
import requests
import re
from typing import Generator, NamedTuple, Dict, List, Iterable, Union
from threading import Thread
from datetime import datetime
import os


def to_file(path: str, data: Iterable, header: Union[None, str, List[str]] = None,
            encoding: str = 'utf-8', sep: str = ';'):
    with open(path, 'w') as wr:
        if header is not None:
            if isinstance(header, str):
                wr.write(header)
            elif isinstance(header, list):
                wr.write(f"{sep}".join(header))
            else:
                raise ValueError
        for line in data:
            try:
                wr.write(f"\n{line}")
            except:
                print(line)
                pass


class Book(NamedTuple):
    product_id: int
    book_name: str
    authors: str
    edition_year: int
    age_restriction: str
    number_pages: int
    genre: str
    link: str
    annotation: str

    def __hash__(self):
        return id(self)

    def to_csv_str(self, delimiter: str = ';', list_delimiter: str = ','):
        return f"{self.product_id}{delimiter}{self.book_name}{delimiter}" \
               f"{self.authors}{delimiter}{self.edition_year}" \
                f"{delimiter}{self.age_restriction}{delimiter}{self.number_pages}" \
                f"{delimiter}{self.genre}{delimiter}{self.link}{delimiter}{self.annotation}"

    def book_str(self, sep: str = ';'):
        return "{1}{0}{2}{0}{3}{0}{4}{0}{5}".format(
            sep, self.product_id, self.book_name, self.edition_year, self.age_restriction,
        self.number_pages)

    @staticmethod
    def header(delimiter: str = ';'):
        return "book_id{0}book_name{0}author{0}edition_year{0}age_restriction{0}number_pages{0}genre{0}link{0}annotation".format(delimiter)

    @staticmethod
    def book_header(sep: str = ';'):
        return "{1}{0}{2}{0}{3}{0}{4}{0}{5}". \
            format(sep, "book_id", "book_name", "price", "edition_year", "editor_name")

    @staticmethod
    def author_book_header(sep: str = ';'):
        return "{1}{0}{2}{0}{3}".format(sep, "author_name", "book_id", "role_name")


def editor_catalog(
        url: str, start_page: int, end_page: int,
        headers: Dict[str, str], no_page_exception: bool = False):
    if start_page > end_page or 0 > start_page or 0 > end_page:
        raise ValueError
    for current_page in range(start_page, end_page + 1):
        try:
            page = requests.get(f"{url}page-{current_page}", headers=headers)
            if page.status_code in {403, 404}:
                if no_page_exception:
                    raise Exception
                else:
                    break
        except:
            break
        tree = html.fromstring(page.content)
        product_id = tree.xpath(
            "//div[@class='product-list__item']/article/div/a/@href")
        links = set(['https://book24.ru' + prod_id for prod_id in product_id])
        try:
            for link in links:
                page = requests.get(link, headers=headers)
                tree = html.fromstring(page.content)
                author = tree.xpath(
                    "//ul[@class='product-characteristic__list']/li[@class='product-characteristic__item-holder']/"
                    "div[@class='product-characteristic__item']/div[@class='product-characteristic__value']//text()"
                )
                labels = tree.xpath(
                    "//ul[@class='product-characteristic__list']/li[@class='product-characteristic__item-holder']/"
                    "div[@class='product-characteristic__item']/div[@class='product-characteristic__label-holder']/"
                    "span[@class='product-characteristic__label']//text()"
                )
                title = tree.xpath(
                    "//div[@class='breadcrumbs product-detail-page__breadcrumbs']/ol/li/span/text()"
                )[0].encode('l1').decode().strip()
                annotation = tree.xpath(
                    "//div[@class='product-about product-detail-page__product-about']/"
                    "div[@class='product-about__additional']/div[@class='product-about__text']//text()"
                )
                prod_id = tree.xpath(
                    "//div[@class='product-detail-page__main-holder']/div[@class='product-detail-page__title-holder']/"
                    "p[@class='product-detail-page__article']/text()")[0].encode('l1').decode().strip().split('Артикул:')[1].strip()
                au_res = [autho.encode('l1').decode() for autho in author]
                la_res = [label.encode('l1').decode() for label in labels]
                ann_res = ' '.join([annotation_res.encode('l1').decode() for annotation_res in annotation])
                dict_ = {'link': link, 'title': title}
                if labels:
                    for i in range(len(labels)):
                        dict_[la_res[i]] = au_res[i]
                yield Book(
                    product_id=prod_id,
                    book_name=title,
                    authors=dict_[' Автор: '],
                    edition_year=int(dict_[' Год издания: ']),
                    age_restriction=dict_[' Возрастное ограничение: '],
                    number_pages=int(dict_[' Количество страниц: ']),
                    genre=dict_[' Раздел: '],
                    link=link,
                    annotation=ann_res,
                )
        except:
            pass


class Controller:
    def __init__(self, encoding: str = 'utf-8'):
        self.processes: List[Thread] = []
        self.encoding = encoding
        self.values: List[str] = []
        self.num_pages = set()
        self.years = set()
        self.authors = set()
        self.books = set()
        self.books_authors = set()
        self.roles = {"автор"}

    def scanner(self, url: str, start_page: int, end_page: int,
                headers: Dict[str, str], no_page_exception: bool = False):
        editor = editor_catalog(url, start_page, end_page, headers, no_page_exception=no_page_exception)
        for ed in editor:
            self.values.append(ed.to_csv_str())

    def start(self, url: str, headers: Dict[str, str], start_page: int, end_page: int,
              process_pages: int, no_page_exception: bool = False):
        cur_start_page = start_page
        cur_end_page = (start_page + process_pages)
        while cur_start_page < end_page:
            self.processes.append(Thread(target=self.scanner, args=(url, cur_start_page, cur_end_page, headers,)))
            self.processes[-1].start()
            cur_start_page += process_pages + 1
            cur_end_page += process_pages + 1

    def join(self):
        for p in self.processes:
            p.join()

    def to_file(self):
        to_file("./book24_catalog.csv", self.values, Book.header())
        # to_file(dir_name + "/books.csv", self.books, Book.book_header())
        # to_file(dir_name + "/years.csv", self.years, "edition_year")
        # to_file(dir_name + "/authors.csv", self.authors, "author_name")
        # to_file(dir_name + "/editors.csv", self.editors, "editor_name")
        # to_file(dir_name + "/roles.csv", self.roles, "role_name")
        # to_file(dir_name + "/authors_books.csv", self.books_authors, Book.author_book_header())


if __name__ == "__main__":
    agent = {
        'user-agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64)'
    }
    base_url = 'https://book24.ru/novie-knigi/'
    # new_books_url = 'https://www.litres.ru/novie/'
    # recommendations_url = 'https://www.litres.ru/recommend/'
    # popular_books_url = 'https://www.litres.ru/luchshie-knigi/'

    controller = Controller('book24.csv')
    controller.start(base_url, agent, 1, 1500, 25)
    # controller.start(new_books_url, agent, 1, 1500, 25)
    # controller.start(recommendations_url, agent, 1, 1500, 25)
    # controller.start(popular_books_url, agent, 1, 1500, 25)

    controller.join()

    controller.to_file()
