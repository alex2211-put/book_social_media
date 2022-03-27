package ru.iliya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.iliya.repositories.BookRepository;
import ru.iliya.services.*;


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
    @Autowired
    BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationApp.class, args);
    }

    @Bean
    public CommandLineRunner springdata() {
        return (args) -> {
            System.out.println("-------StartTest--------");
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
            System.out.println(bCryptPasswordEncoder.encode("adm1nadm!n"));
            System.out.println(bCryptPasswordEncoder.encode("!devel0per"));
            System.out.println(bCryptPasswordEncoder.encode("юзерИзКит@я"));
            System.out.println("----------end-----------");
        };
    }
}