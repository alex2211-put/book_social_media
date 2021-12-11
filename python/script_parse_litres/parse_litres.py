import book
from lxml import html
import path_in_tree
import requests
from threading import Thread
from typing import Dict
from typing import Iterable
from typing import List
from typing import Union


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

    def scanner(
            self,
            url: str,
            start_page: int,
            end_page: int,
            headers: Dict[str, str],
            no_page_exception: bool = False,
    ):
        editor = _editor_catalog(url, start_page, end_page, headers, no_page_exception=no_page_exception)
        for ed in editor:
            self.values.append(ed.to_csv_str())

    def start(self, url: str, headers: Dict[str, str], start_page: int, end_page: int,
              process_pages: int):
        cur_start_page = start_page
        cur_end_page = (start_page + process_pages)
        while cur_start_page < end_page:
            self.processes.append(
                Thread(target=self.scanner, args=(url, cur_start_page, cur_end_page, headers,)),
            )
            self.processes[-1].start()
            cur_start_page += process_pages + 1
            cur_end_page += process_pages + 1

    def join(self):
        for p in self.processes:
            p.join()

    def to_file(self):
        _to_file("./book24_catalog.csv", self.values, book.Book.header())


def _to_file(
        path: str,
        data: Iterable,
        header: Union[None, str, List[str]] = None,
):
    with open(path, 'w') as wr:
        if header is not None:
            if isinstance(header, str):
                wr.write(header)
            else:
                raise ValueError
        for line in data:
            try:
                wr.write(f"\n{line}")
            except:
                print(line)
                pass


def _editor_catalog(
        url: str,
        start_page: int,
        end_page: int,
        headers: Dict[str, str],
        no_page_exception: bool = False,
):
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
        book_links = tree.xpath(path_in_tree.BOOK_LINKS_PATH)
        links = set(['https://book24.ru' + book_link for book_link in book_links])
        try:
            for link in links:
                page = requests.get(link, headers=headers)
                tree = html.fromstring(page.content)
                characteristic_values = tree.xpath(path_in_tree.CHARACTERISTIC_VALUES_PATH)
                characteristic_labels = tree.xpath(path_in_tree.CHARACTERISTIC_LABELS_PATH)
                title = tree.xpath(path_in_tree.TITLE_PATH)[0].encode('l1').decode().strip()
                annotation = tree.xpath(path_in_tree.ANNOTATION_PATH)
                product_id = tree.xpath(path_in_tree.PRODUCT_ID_PATH)[0].encode('l1').decode().strip().split(
                    'Артикул:')[1].strip()

                characteristic_values_res = [autho.encode('l1').decode() for autho in characteristic_values]
                characteristic_labels_res = [label.encode('l1').decode() for label in characteristic_labels]
                ann_res = ' '.join([annotation_res.encode('l1').decode().replace('\n', ' ') for annotation_res in annotation])
                dict_ = {}
                if characteristic_labels:
                    for i in range(len(characteristic_labels)):
                        dict_[characteristic_labels_res[i]] = characteristic_values_res[i]
                yield book.Book(
                    product_id=product_id,
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


if __name__ == "__main__":
    agent = {
        'user-agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64)'
    }
    base_url = 'https://book24.ru/novie-knigi/'

    controller = Controller('book24.csv')
    controller.start(base_url, agent, 1, 1500, 25)
    controller.join()
    controller.to_file()
