package ru.iliya.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.iliya.entities.User;
import ru.iliya.security.SecurityUserConverter;
import ru.iliya.services.*;
import ru.iliya.entities.Comments;
import ru.iliya.entities.Recommendations;

import java.util.List;
import java.util.Objects;

@Controller
public class BookSearchController {

    @Autowired
    BookSearchService bookSearchService;
    @Autowired
    MarksServiceImpl marksServiceImpl;
    @Autowired
    RecommendationsService recommendationsService;
    @Autowired
    SecurityUserConverter securityUserConverter;
    @Autowired
    FavouritesService favouritesService;

    @GetMapping("/book/search") //book/search
    public String showBooksByTitle(@RequestParam(name = "title", required = false, defaultValue = "") String title,
                                   @RequestParam(name = "author", required = false, defaultValue = "") String author,
                                   @RequestParam(name = "genre", required = false, defaultValue = "") String genre,
                                   Model model) {
        model.addAttribute("books",
                bookSearchService.findBooksByTitleAuthorGenre(title, author, genre));
        return "book-search"; //view
    }



    @RequestMapping(value = "/do-stuff/{book_id}/{mark}")
    public String doStuffMethod(@PathVariable(name = "book_id") String book_id,
                                @PathVariable(name = "mark") String mark,
                                @AuthenticationPrincipal UserDetails currentUser) {
        User user = securityUserConverter.getUserByDetails(currentUser);
        marksServiceImpl.setMarksByBookIdAndUserId(Integer.parseInt(book_id), user.getUserID(), Integer.parseInt(mark));
        return "redirect:/book/info/" + book_id;
    }


}
