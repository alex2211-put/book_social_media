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

import ru.iliya.entities.Friends;
import ru.iliya.entities.User;
import ru.iliya.security.SecurityUserConverter;
import ru.iliya.services.FavouritesService;
import ru.iliya.services.FriendsServiceImpl;
import ru.iliya.services.RecommendationsService;
import ru.iliya.services.UserServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.ArrayList;
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

    private class Person {
        public User user;
        public Friends friends;

        public Person(User user, Friends friends) {
            this.user = user;
            this.friends = friends;
        }
    }

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

    @PostMapping("/user/info/addFriends/{user_id}/{friends}")
    public String addFriends(@PathVariable(name = "user_id") int user_id,
                             @PathVariable(name = "friends") String friends,
                             @AuthenticationPrincipal UserDetails currentUser,
                             Model model) {
        User user = securityUserConverter.getUserByDetails(currentUser);
        switch (friends) {
            case ("Add to friends") -> {
                friendsService.setFriendsByUserIDAndUser2ID(user.getUserID(), user_id);
                if (friendsService.findByUserIDAndUser2ID(user_id, user.getUserID()).size() == 2) {
                    friends = "Remove from friends";
                } else {
                    friends = "Unsent";
                }
            }
            case ("Remove from friends") -> {
                friendsService.deleteFriendsByFriendID(friendsService.findByUserIDAndUser2ID(user.getUserID(), user_id).get(0).getFriendID());
                friends = "Add to friends";
            }
            case ("Unsent") -> {
                friendsService.deleteFriendsByFriendID(friendsService.findByUserIDAndUser2ID(user.getUserID(), user_id).get(0).getFriendID());
                friends = "Add to friends";
            }
        }
        return showUserInfo(user_id, friends, currentUser, model);
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

    @GetMapping("/user/friends")
    public String showUserFriends(@AuthenticationPrincipal UserDetails currentUser,
                                  Model model) {

        User user = securityUserConverter.getUserByDetails(currentUser);
        List<User> res = getFriendsForUser(user);
        List<Person> personList = new ArrayList<>();
        for (User user1 : res) {
            personList.add(new Person(user1, null));
        }
        model.addAttribute("personList", personList);
        return "friends";
    }

    private List<User> getFriendsForUser(User user) {
        List<Friends> friends = friendsService.findByUserID(user.getUserID());
        List<Friends> friends1 = friendsService.findByUser2ID(user.getUserID());
        List<User> users = friends.stream().map(
                friend -> userService.findUserByUserID(friend.getUser2ID())
        ).toList();
        List<User> users2 = friends1.stream().map(
                friend -> userService.findUserByUserID(friend.getUserID())
        ).toList();
        List<User> res = new ArrayList<>();
        for (User user1 : users) {
            if (users2.contains(user1))
                res.add(user1);
        }
        return res;
    }

    @GetMapping("/user/friends/incoming")
    public String showUserFriendsIncoming(@AuthenticationPrincipal UserDetails currentUser,
                                          Model model) {

        User user = securityUserConverter.getUserByDetails(currentUser);
        List<User> res = getFriendsForUser(user);
        List<Integer> idUsers = res.stream().map(
                User::getUserID
        ).toList();
        List<Friends> friends = friendsService.findByUser2ID(user.getUserID());
        List<Person> personList = new ArrayList<>();
        for (Friends friend : friends) {
            User user1 = userService.findUserByUserID(friend.getUserID());
            if (idUsers.contains(user1.getUserID())) {
                continue;
            } else {
                personList.add(new Person(user1, friend));
            }
        }
        model.addAttribute("personList", personList);
        return "friends";
    }

    @GetMapping("/user/friends/outgoing")
    public String showUserFriendsOutgoing(@AuthenticationPrincipal UserDetails currentUser,
                                          Model model) {

        User user = securityUserConverter.getUserByDetails(currentUser);
        List<User> res = getFriendsForUser(user);
        List<Integer> idUsers = res.stream().map(
                User::getUserID
        ).toList();
        List<Friends> friends = friendsService.findByUserID(user.getUserID());
        List<Person> personList = new ArrayList<>();
        for (Friends friend : friends) {
            User user1 = userService.findUserByUserID(friend.getUser2ID());
            if (idUsers.contains(user1.getUserID())) {
                continue;
            } else {
                personList.add(new Person(user1, friend));
            }
        }
        model.addAttribute("personList", personList);
        return "friends";
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

