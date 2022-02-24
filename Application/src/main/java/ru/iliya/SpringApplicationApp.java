package ru.iliya;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.iliya.entities.Author;
import ru.iliya.entities.Book;
import ru.iliya.repositories.BookRepository;


@SpringBootApplication
public class SpringApplicationApp {

    @Autowired
    BookSearchService bookSearchService;
    @Autowire
    MarksService marksService;

    CommentService commentService;
    @Autowired
    SpringApplicationData springApplicationData;


    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationApp.class, args);
    }

    @Bean
    public CommandLineRunner springdata() {
        return(args) -> {

            System.out.println("-------StartTest--------");
            //System.out.println(bookSearchService.findBooksByGenre("Русские детективы"));
           Book book = bookSearchService.findBookByTitle("Часы Зигмунда Фрейда");
           int bookId = book.getBookID();
           marksService.setMarksByBookIdAndUserId(bookId, 1234, 228);
           System.out.println(marksService.findByBookIdAndUserId(bookId, 1234));
            System.out.println(bookSearchService.findBooksByGenre("Русские детективы"));
            commentService.setCommentByBookId(6038852, "Very nice book, love it");
            System.out.println(commentService.findCommentsByBookId(6038852));

            System.out.println("----------end-----------");
        };
    }

}
