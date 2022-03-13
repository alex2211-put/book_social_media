package ru.iliya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.iliya.entities.Book;
import ru.iliya.entities.Message;
import ru.iliya.services.BookSearchService;
import ru.iliya.services.MarksService;
import ru.iliya.services.MessageService;


@SpringBootApplication
public class SpringApplicationApp {

    @Autowired
    BookSearchService bookSearchService;
    @Autowired
    MarksService marksService;
    @Autowired
    RecommendationsService recommendationsService;
    @Autowired
    BlockedUsersService blockedUsersService;
//    CommentService commentService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    FavouritesServiceImpl favouritesService;
    @Autowired
    SpringApplicationData springApplicationData;
    @Autowired
    MessageService messageService;

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationApp.class, args);
    }

    @Bean
    public CommandLineRunner springdata() {
        return (args) -> {
            System.out.println("-------StartTest--------");
            System.out.println(userService.findUserByEmail("x@gmail.com"));
            System.out.println(userService.findUserByLastName("Boba"));
            System.out.println(userService.findUserByFirstName("Biba"));
            System.out.println(userService.findUserByUserID(1));
            System.out.println(userService.findUserByNickname("xxx"));
            System.out.println("----------end-----------");
        };
    }
}