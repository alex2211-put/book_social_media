package ru.iliya;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.iliya.repositories.BaseRepository;

@Controller
public class BookSearchController {

    @Autowired
    BaseRepository baseRepository;

    String title;
    @GetMapping("/book-by-title")
    public String showBooksByTitle(Model model) {
        if (title != null)
            model.addAttribute("book", baseRepository.getByTitle(title));
        return "book-by-title"; //view
    }

    @PostMapping("/book-by-title")
    public String searchBookByTitle(@RequestParam(name = "title") String title) {
        this.title = title;

        return "redirect:/book-by-title";
    }

}
