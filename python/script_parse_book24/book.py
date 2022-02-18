from typing import NamedTuple


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

    def to_csv_str(self, delimiter: str = ';') -> str:
        return f"{self.product_id}{delimiter}" \
               f"{self.book_name}{delimiter}" \
               f"{self.authors}{delimiter}" \
               f"{self.edition_year}{delimiter}" \
               f"{self.age_restriction}{delimiter}" \
               f"{self.number_pages}{delimiter}" \
               f"{self.genre}{delimiter}" \
               f"{self.link}{delimiter}" \
               f"{self.annotation}"

    def book_str(self, sep: str = ';'):
        return "{1}{0}{2}{0}{3}{0}{4}{0}{5}".format(
            sep,
            self.product_id,
            self.book_name,
            self.edition_year,
            self.age_restriction,
            self.number_pages,
        )

    @staticmethod
    def header(delimiter: str = ';'):
        return "book_id{0}" \
               "book_name{0}" \
               "author{0}" \
               "edition_year{0}" \
               "age_restriction{0}" \
               "number_pages{0}" \
               "genre{0}" \
               "link{0}" \
               "annotation".format(delimiter)

    @staticmethod
    def book_header(sep: str = ';'):
        return "{1}{0}{2}{0}{3}{0}{4}{0}{5}".format(
            sep,
            "book_id",
            "book_name",
            "price",
            "edition_year",
            "editor_name",
        )
