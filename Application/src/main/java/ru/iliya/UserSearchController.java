package ru.iliya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserSearchController {

    @Autowired
    UserServiceImpl userService;

    String email;
    @GetMapping("/user_search") //user/search        value   showUsers
    public String showUsersByEmail(@RequestParam(name = "search", required = false, defaultValue = "") String search,
                                   Model model) {
        model.addAttribute("users",
                userService.findUserByFirstNameLastNameNickNameEmail(search));
        return "user_search"; //view
    }

//    @GetMapping("/book-by-title")
//    public String searchBookByTitle(@RequestParam(name = "title") String title) {
//        this.title = title;
//
//        return "redirect:/book-by-title";
//    }

    @GetMapping("/user/info")
    public String showBookInfo(@RequestParam(name = "user_id", required = false, defaultValue = "1") int user_id,
                               Model model) {
        model.addAttribute("user",
                userService.findUserByUserID(user_id));
        return "user";
    }

}
