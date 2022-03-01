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
    UserServiceImpl userService;
    @Autowired
    FavouritesServiceImpl favouritesService;
    @Autowired
    RecommendationsServiceImpl recommendationsService;
    @Autowired
    BlockedUsersServiceImpl blockedUsersService;
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
            userService.setUserByParams( "xaero", "Roma", "Mikhailov",   null,
                    "ziroxxx3@gmail.com", true, "sdgdg", 1);
            userService.setUserByParams( "ziro", "Sasha", "Putin",   null,
                    "putin@gmail.com", true, "sdgdf", 2);
//            System.out.println(userService.findUserByFirstName("Roma"));
//            System.out.println(userService.findUserByEmail("ziroxxx3@gmail.com"));
//            System.out.println(userService.findUserByNickname("xaero"));
//            System.out.println(userService.findUserByUserID(1));
//            System.out.println(userService.findUserByUserID(2));
            blockedUsersService.setBlockedUsersByParams(1, 2);
            System.out.println(blockedUsersService.findByUserIDBlocked(2));
            blockedUsersService.deleteBlockedUsersByBlockID(1);
//            favouritesService.setFavouritesByParams(1, 1);
//            System.out.println(favouritesService.findFavouritesByUserID(1));
//            favouritesService.deleteFavouritesByLinkID(1);
//            recommendationsService.setRecommendationsByParams(1,1);
//            System.out.println(recommendationsService.findRecommendationsByUserID(1));
//            recommendationsService.deleteRecommendationsByRecommendationsID(1);
            System.out.println("----------end-----------");
        };
    }
}
