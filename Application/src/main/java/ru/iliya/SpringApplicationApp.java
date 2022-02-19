package ru.iliya;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.iliya.entities.Author;
import ru.iliya.repositories.BookRepository;


@SpringBootApplication
public class SpringApplicationApp {

    @Autowired
    BookSearchService bookSearchService;
    @Autowired
    SpringApplicationData springApplicationData;

    @Autowired
    BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationApp.class, args);
    }

    @Bean
    public CommandLineRunner springdata() {
        return(args) -> {

            System.out.println("-------StartTest--------");
            //System.out.println(bookSearchService.findBooksByGenre("Русские детективы"));
            System.out.println(bookSearchService.findBookByTitle("Часы Зигмунда Фрейда"));
            System.out.println(bookSearchService.findBooksByAuthor("Александр", "Левитас"));

            System.out.println("----------end-----------");
        };
    }

}
