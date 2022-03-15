package ru.iliya;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.iliya.services.BookSearchService;
import ru.iliya.repositories.BaseRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookSearchController {

    @Autowired
    BookSearchService bookSearchService;

    String title;
    @GetMapping("/book-by-title") //book/search
    public String showBooksByTitle(@RequestParam(name = "title", required = false, defaultValue = "") String title,
                                   @RequestParam(name = "name", required = false, defaultValue = "") String name,
                                   @RequestParam(name = "genre", required = false, defaultValue = "") String genre,
                                   Model model) {
        model.addAttribute("books",
                bookSearchService.findBooksByTitleAuthorGenre(title, name, genre));
        return "book-by-title"; //view
    }

//    @GetMapping("/book-by-title")
//    public String searchBookByTitle(@RequestParam(name = "title") String title) {
//        this.title = title;
//
//        return "redirect:/book-by-title";
//    }

}
