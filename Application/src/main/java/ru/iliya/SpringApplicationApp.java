package ru.iliya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.iliya.entities.Book;
import ru.iliya.entities.Message;
import ru.iliya.repositories.BaseRepository;
import ru.iliya.repositories.BookRepository;
import ru.iliya.repositories.FriendsRepository;
import ru.iliya.services.BookSearchService;
import ru.iliya.services.MarksService;
import ru.iliya.services.MessageService;

import java.util.List;


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
    @Autowired
    FriendsRepository friendsRepository;
    @Autowired
    BaseRepository baseRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationApp.class, args);
    }

    @Bean
    public CommandLineRunner springdata() {
        return (args) -> {
            System.out.println("-------StartTest--------");
            System.out.println("adm1nadm!n".hashCode());
            System.out.println("!devel0per".hashCode());
            System.out.println("юзерИзКит@я".hashCode());

//            baseRepository.setFriendsByUserIDAndUser2ID(1, 5);
            System.out.println(friendsRepository.findByUserIDAndUser2ID(1, 5));
//            baseRepository.deleteFriendsByFriendID(3);
            System.out.println(userService.findUserByFirstNameLastNameNickNameEmail("alik"));
            System.out.println("----------end-----------");
        };
    }
}