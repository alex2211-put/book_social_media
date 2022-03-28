package ru.iliya.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.iliya.entities.User;
import ru.iliya.security.SecurityUserConverter;
import ru.iliya.services.RecommendationsService;
import ru.iliya.entities.Book;
import ru.iliya.entities.Comments;
import ru.iliya.entities.Recommendations;
import ru.iliya.services.BookSearchService;
import ru.iliya.services.MarksService;

import java.util.List;
import java.util.Objects;

@Controller
public class BookSearchController {

    @Autowired
    BookSearchService bookSearchService;
    @Autowired
    MarksService marksService;
    @Autowired
    RecommendationsService recommendationsService;
    @Autowired
    SecurityUserConverter securityUserConverter;


    @GetMapping("/book/search") //book/search
    public String showBooksByTitle(@RequestParam(name = "title", required = false, defaultValue = "") String title,
                                   @RequestParam(name = "name", required = false, defaultValue = "") String name,
                                   @RequestParam(name = "genre", required = false, defaultValue = "") String genre,
                                   Model model) {
        model.addAttribute("books",
                bookSearchService.findBooksByTitleAuthorGenre(title, name, genre));
        return "book-search"; //view
    }

    @GetMapping("/book/info/{book_id}")
    public String showBookInfo(@PathVariable(name = "book_id") String book_id,
                               @RequestParam(name = "favourites", required = false, defaultValue = "Add to favourites") String favourites,
                               Model model) {
        model.addAttribute("mark", marksService.findByBookIdAndUserId(Integer.parseInt(book_id), 4));
        Recommendations recommendations = recommendationsService.findRecommendationByUserIdAndBookId(4, Integer.parseInt(book_id));
        if (recommendations == null) {
            recommendations = new Recommendations();
            recommendations.setRecommendationID(-1);
        }
        model.addAttribute("recommendation", recommendations);
        List<Comments> comments = bookSearchService.getComments(book_id);
        model.addAttribute("comments", comments);
        model.addAttribute("book",
                bookSearchService.findBookById(book_id));
        model.addAttribute("favourites", favourites);
        return "book";
    }

    @PostMapping("/book/info/addFavourites/{book_id}/{favourites}")
    public String addFavourites(@PathVariable(name = "book_id") String book_id,
                                @PathVariable(name = "favourites") String favourites,
                                Model model) {
        if (Objects.equals(favourites, "Add to favourites")) {
            favourites = "Remove from favourites";
        } else {
            favourites = "Add to favourites";
        }
        return showBookInfo(book_id, favourites, model);
    }

    @PostMapping("/book/info/addComment/{book_id}")
    public String addComment(@PathVariable(name = "book_id") String book_id,
                             @RequestParam(name = "comment") String comment,
                             @AuthenticationPrincipal UserDetails currentUser,
                             Model model) {
        User user = securityUserConverter.getUserByDetails(currentUser);
        bookSearchService.addComment(book_id, String.valueOf(user.getUserID()), comment);
        return "redirect:/book/info/" + book_id;
    }

    @RequestMapping(value = "/do-stuff/{book_id}/{mark}")
    public String doStuffMethod(@PathVariable(name = "book_id") String book_id,
                                @PathVariable(name = "mark") String mark,
                                @AuthenticationPrincipal UserDetails currentUser) {
        User user = securityUserConverter.getUserByDetails(currentUser);
        marksService.setMarksByBookIdAndUserId(Integer.parseInt(book_id), user.getUserID(), Integer.parseInt(mark));
        return "redirect:/book/info/" + book_id;
    }

    @RequestMapping(value = "/book/reload_mark/{book_id}")
    public String reloadMark(@PathVariable(name = "book_id") String book_id,
                             @AuthenticationPrincipal UserDetails currentUser) {
        User user = securityUserConverter.getUserByDetails(currentUser);
        marksService.deleteMarkByBookIdAndUserId(Integer.parseInt(book_id), user.getUserID());
        return "redirect:/book/info/" + book_id;
    }

    @RequestMapping(value = "/book/info/set_recommendation/{book_id}/{recommendation}")
    public String makeRecommendation(@PathVariable(name = "recommendation") String rec,
                                     @PathVariable(name = "book_id") String book_id) {
        System.out.println(rec);
        if (Integer.parseInt(rec) == -1) {
            recommendationsService.setRecommendationsByParams(4, Integer.parseInt(book_id));
        } else {
            Recommendations rec_ = recommendationsService.findRecommendationByRecommendationId(Integer.parseInt(rec));
            recommendationsService.deleteRecommendationsByRecommendationsID(rec_.getRecommendationID());
        }
        return "redirect:/book/info/" + book_id;
    }
}
