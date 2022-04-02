package ru.iliya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iliya.entities.User;
import ru.iliya.services.UserServiceImpl;

import java.util.Date;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserSearchController {

    @Autowired
    UserServiceImpl userService;

    String email;

    @GetMapping("/user_search") //user/search        value   showUsers
    public String showUsersByEmail(@RequestParam(name = "search", required = false, defaultValue = " ") String search,
                                   Model model) {
        List<User> userList = userService.findUserByFirstNameLastNameNickNameEmail(search);
        if (userList.size() == 1 && userList.get(0) == null) {
            userList.clear();
        }
        model.addAttribute("users", userList);
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

    @GetMapping("/user/info/{user_id}")
    public String showUserInfo(@PathVariable(name = "user_id") int user_id,
                               Model model) throws Exception {
        model.addAttribute("user",
                userService.findUserByUserID(user_id));
        return "registration";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/new/user")
    public String afterRegister(@RequestParam(name = "firstName") String firstName,
                                @RequestParam(name = "lastName") String lastName,
                                @RequestParam(name = "nickname") String nickname,
                                @RequestParam(name = "email") String email,
                                @RequestParam(name = "password") String password,
                                Model model) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        try{
            userService.setUserByParams(nickname, firstName, lastName, new Date(), email, true, bCryptPasswordEncoder.encode(password), 2, null);
        } catch (Exception exc) {
            model.addAttribute("firstName", firstName);
            model.addAttribute("lastName", lastName);
            model.addAttribute("nickname", nickname);
            model.addAttribute("email", email);
            if (userService.findUserByEmail(email).size() != 0)
            {
                return "err_reg_email";
            }
            if (userService.findUserByNickname(nickname) != null)
            {
                return "err_reg_nick";
            }
            else return "error-page";
        }
        return "login";
    }

}
