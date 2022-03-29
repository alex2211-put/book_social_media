package ru.iliya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iliya.FavouritesService;
import ru.iliya.UserService;
import ru.iliya.UserServiceImpl;
import ru.iliya.entities.Friends;
import ru.iliya.services.FriendsService;

import java.util.Objects;


@Controller
public class UserSearchController {

    @Autowired
    UserService userService;
    @Autowired
    FriendsService friendsService;
    @Autowired
    FavouritesService favouritesService;

    @GetMapping("/user_search") //user/search        value   showUsers
    public String showUsersBySearch(@RequestParam(name = "search") String search,
                                   Model model) {
        model.addAttribute("users",
                userService.findUserByFirstNameLastNameNickNameEmail(search));
        System.out.println(search);
        System.out.println(userService.findUserByFirstNameLastNameNickNameEmail("alik"));
        return "user_search"; //view
    }

    @GetMapping("/user/info")
    public String showBookInfo(@RequestParam(name = "user_id", required = false, defaultValue = "1") int user_id,
                               Model model) {
        model.addAttribute("user",
                userService.findUserByUserID(user_id));
        return "user";
    }

    @GetMapping("/user/info/{user_id}")
    public String showUserInfo(@PathVariable(name = "user_id") int user_id,
                               @RequestParam(name = "friends", required = false, defaultValue = "Add to friends") String friends,
                               Model model) {
        model.addAttribute("user",
                userService.findUserByUserID(user_id));
        model.addAttribute("friends", friends);
        model.addAttribute("favourites", favouritesService.findFavouritesByUserID(user_id));
        return "user";
    }

    @PostMapping("/user/info/addFriends/{user_id}/{friends}")
    public String addFriends(@PathVariable(name = "user_id") int user_id,
                             @PathVariable(name = "friends") String friends,
                                Model model) {
        if (Objects.equals(friends, "Add to friends")) {
            friends = "Remove from friends";
        } else {
            friends = "Add to friends";
        }
        return showUserInfo(user_id, friends, model);
    }

}
