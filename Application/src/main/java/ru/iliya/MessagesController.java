package ru.iliya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iliya.repositories.MongoRepositoryImpl;

@Controller
public class MessagesController {

    @Autowired
    MongoRepositoryImpl mongoRepository;

    String title;
    @GetMapping("/book-by-title")
    public String showBooksByTitle(Model model) {
        return "book-by-title"; //view
    }

    @PostMapping("/book-by-title")
    public String searchBookByTitle(@RequestParam(name = "title") String title) {
        this.title = title;

        return "redirect:/book-by-title";
    }

}
