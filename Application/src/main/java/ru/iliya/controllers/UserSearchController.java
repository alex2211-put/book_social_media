package ru.iliya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.iliya.entities.User;
import ru.iliya.security.SecurityUserConverter;
import ru.iliya.services.FavouritesService;
import ru.iliya.services.FriendsService;
import ru.iliya.services.UserServiceImpl;

import java.awt.*;
import java.util.List;
import java.util.Objects;


@Controller
public class UserSearchController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    FavouritesService favouritesService;
    @Autowired
    SecurityUserConverter securityUserConverter;
    @Autowired
    FriendsService friendsService;


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
        model.addAttribute("favourites", favouritesService.findFavouritesByUserID(user_id));
        model.addAttribute("currentUser", user);
        return "user";
    }

    @PostMapping("/user/info/addFriends/{user_id}/{friends}")
    public String addFriends(@PathVariable(name = "user_id") int user_id,
                             @PathVariable(name = "friends") String friends,
                             @AuthenticationPrincipal UserDetails currentUser,
                             Model model) {
        User user = securityUserConverter.getUserByDetails(currentUser);
        switch (friends) {
            case  ("Add to friends"):
                friendsService.setFriendsByUserIDAndUser2ID(user.getUserID(),  user_id);
                if(friendsService.findByUserIDAndUser2ID(user_id, user.getUserID()).size() == 2) {
                    friends = "Remove from friends";
                }
                else {
                    friends = "Sent";
                }
                break;
            case ("Remove from friends"):
                friendsService.deleteFriendsByFriendID(friendsService.findByUserIDAndUser2ID(user.getUserID(), user_id).get(0).getFriendID());
                friends = "Add to friends";
                break;
            case ("Sent"):
                friendsService.deleteFriendsByFriendID(friendsService.findByUserIDAndUser2ID(user.getUserID(), user_id).get(0).getFriendID());
                friends = "Add to friends";
                break;
        }
        return showUserInfo(user_id, friends, currentUser, model);
    }
}
