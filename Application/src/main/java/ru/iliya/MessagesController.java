package ru.iliya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iliya.repositories.MongoRepositoryImpl;
import ru.iliya.services.MessageService;

@Controller
public class MessagesController {

    @Autowired
    MessageService messageService;

    String title;
    @GetMapping("/all_dialogs_for_user") //book/search
    public String showAllDialogsForUser(@RequestParam(name = "user") String user,
                                   Model model) {
        model.addAttribute("dialogs",
                messageService.getDialogsForUser(user));
        return "all-dialogs-for-user"; //view
    }

//    @GetMapping("/book-by-title")
//    public String searchBookByTitle(@RequestParam(name = "title") String title) {
//        this.title = title;
//
//        return "redirect:/book-by-title";
//    }


}
