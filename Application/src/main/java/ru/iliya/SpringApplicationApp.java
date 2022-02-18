package ru.iliya;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringApplicationApp {

    @Autowired
    BookSearchService bookSearchService;
    @Autowired
    CommentService commentService;
    @Autowired
    SpringApplicationData springApplicationData;

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationApp.class, args);}

    @Bean
    public CommandLineRunner springdata() {
        return(args) -> {

            System.out.println("-------StartTest--------");
            System.out.println(bookSearchService.findBooksByGenre("Русские детективы"));
            commentService.setCommentByBookId(6038852, "Very nice book, love it");
            System.out.println(commentService.findCommentsByBookId(6038852));

            System.out.println("----------end-----------");
        };
    }

}
