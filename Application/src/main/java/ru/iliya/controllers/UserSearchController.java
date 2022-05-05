package ru.iliya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iliya.entities.User;
import ru.iliya.security.SecurityUserConverter;
import ru.iliya.services.FavouritesService;
import ru.iliya.services.FriendsServiceImpl;
import ru.iliya.services.RecommendationsService;
import ru.iliya.services.UserServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class UserSearchController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    FavouritesService favouritesService;
    @Autowired
    SecurityUserConverter securityUserConverter;
    @Autowired
    FriendsServiceImpl friendsService;
    @Autowired
    RecommendationsService recommendationsService;

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

    @GetMapping("/user/info/{user_id}")
    public String showUserInfo(@PathVariable(name = "user_id") int user_id,
                               @RequestParam(name = "friends", required = false, defaultValue = "Add to friends") String friends,
                               @AuthenticationPrincipal UserDetails currentUser,
                               Model model) {
        User user = securityUserConverter.getUserByDetails(currentUser);
        model.addAttribute("user",
                userService.findUserByUserID(user_id));
        model.addAttribute("friends", friends);
        model.addAttribute("recommendations", recommendationsService.findRecommendationsByUserID(user_id));
        model.addAttribute("currentUser", user);
        return "user";
    }

    @GetMapping("/user/info")
    public String showUserInfo(@AuthenticationPrincipal UserDetails currentUser,
                               Model model) {
        User user = securityUserConverter.getUserByDetails(currentUser);
        model.addAttribute("user",
                userService.findUserByUserID(user.getUserID()));
        model.addAttribute("user_id", user.getUserID());
        model.addAttribute("currentUser", user);
        model.addAttribute("recommendations", recommendationsService.findRecommendationsByUserID(user.getUserID()));
        return "user";
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
                                @RequestParam(name = "birthdate") String birthdate,
                                @RequestParam(name = "imagelink") String imagelink,
                                Model model) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date date = parser.parse(birthdate);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        try {
            userService.setUserByParams(nickname, firstName, lastName, date, email, true, bCryptPasswordEncoder.encode(password), 2, imagelink);
        } catch (Exception exc) {
            model.addAttribute("firstName", firstName);
            model.addAttribute("lastName", lastName);
            model.addAttribute("nickname", nickname);
            model.addAttribute("email", email);
            model.addAttribute("imagelink", imagelink);
            model.addAttribute("birthdate", birthdate);
            if (userService.findUserByEmail(email).size() != 0) {
                return "err_reg_email";
            }
            if (userService.findUserByNickname(nickname) != null) {
                return "err_reg_nick";
            } else return "error-page";
        }
        return "login";
    }
}

