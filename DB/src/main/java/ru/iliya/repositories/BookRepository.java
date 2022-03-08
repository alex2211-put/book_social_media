package ru.iliya.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.iliya.entities.Author;
import ru.iliya.entities.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select b from Book b where b.title = :title")
    Book getByTitle(@Param("title") String title);

    List <Book> findBooksByTitleLike(String regexp);
    List <Book> findBooksByTitle(String title);
    List<Book> findByAuthors(List <Author> authors);
    List<Book> findBooksByGenre(String genre);
    Book findBookByBookID(Integer bookId);
    List <Book> findBooksByTitleLikeAndAuthorsAndGenre(String title,
                                                   List <Author> authors,
                                                   String genre);
    List <Book> findBooksByTitleAndAuthorsAndGenre(String title,
                                                   List <Author> authors,
                                                   String genre);
    List <Book> findBooksByTitleLikeAndAuthors(String title,
                                           List <Author> authors);
    List <Book> findBooksByTitleAndAuthors(String title,
                                           List <Author> authors);
    List <Book> findBooksByTitleLikeAndGenre(String title,
                                         String genre);
    List <Book> findBooksByTitleAndGenre(String title,
                                         String genre);
    List <Book> findBooksByAuthorsAndGenre(List <Author> authors,
                                           String genre);
}
