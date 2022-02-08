CREATE DATABASE book_social_media;

CREATE SCHEMA catalog;

CREATE TABLE catalog.Book(
                             book_id INT PRIMARY KEY,
                             title VARCHAR(255) NOT NULL,
                             year_release DATA,
                             genre VARCHAR(100),
                             link_internet VARCHAR(255) NOT NULL,
                             age_restriction INT,
                             number_pages INT,
                             annotation VARCHAR(255),
                             author_id INT REFERENCES catalog.Author(author_id) NOT NULL,
);

CREATE TABLE catalog.Author(
                               author_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                               first_name VARCHAR(255),
                               last_name VARCHAR(255),
                               birthdate DATE,
                               country VARCHAR(255),
);

CREATE TABLE catalog.AuthorBook(
                                   author_id INT REFERENCES catalog.Author(author_id),
                                   book_id INT REFERENCES catalog.Book(book_id),
                                   CONSTRAINT PK_AuthorBook PRIMARY KEY (author_id, book_id)
);

DO $$
    DECLARE
        csv_path VARCHAR(255) := './';
    BEGIN
        CREATE TEMPORARY TABLE temp_author(author VARCHAR(255));
        CREATE TEMPORARY TABLE temp_book(book_id INT, book_name VARCHAR(255), price NUMERIC(9,2), edition_year SMALLINT, editor_name VARCHAR(255));
        CREATE TEMPORARY TABLE temp_author_book(author_name VARCHAR(255), book_id INT, role_name VARCHAR(50));
    EXECUTE 'COPY temp_author(author) FROM ' || quote_literal(concat(csv_path, 'book24_catalog.csv')) || 'WITH (FORMAT csv, HEADER true)';
        INSERT INTO catalog.Author(author_name) SELECT author_name FROM temp_author;
    EXECUTE 'COPY temp_book(book_id, book_name, edition_year, age_restriction, number_pages, genre, link, annotation) FROM '
                || quote_literal(concat(csv_path, 'book24_catalog.csv'))
                || 'WITH (FORMAT csv, HEADER true, DELIMITER ' || quote_literal(';') || ')';
        INSERT INTO catalog.book(book_id, title, year_release, age_restriction, number_pages, genre, link_internet, annotation)
        SELECT
            TB.book_id,
            TB.book_name,
            TB.edition_year,
            TB.age_restriction,
            TB.number_pages,
            TB.genre,
            TB.link,
            TB.annotation,
        FROM temp_book AS TB;

    EXECUTE 'COPY temp_author_book(author_name, book_id) FROM '
                || quote_literal(concat(csv_path, 'book24_catalog.csv'))
                || 'WITH (FORMAT csv, HEADER true, DELIMITER ' || quote_literal(';') || ')';
        INSERT INTO catalog.AuthorBook(author_id, book_id, role_id)
        SELECT
            (SELECT A.author_id FROM catalog.Author AS A WHERE A.author_name = TAB.author_name),
            book_id,
        FROM temp_author_book AS TAB;
    END;
    $$ LANGUAGE plpgsql;
