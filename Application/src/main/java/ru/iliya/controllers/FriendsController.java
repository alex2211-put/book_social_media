package ru.iliya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.iliya.entities.Friends;
import ru.iliya.entities.User;
import ru.iliya.helpers.Person;
import ru.iliya.security.SecurityUserConverter;
import ru.iliya.services.FavouritesService;
import ru.iliya.services.FriendsServiceImpl;
import ru.iliya.services.RecommendationsService;
import ru.iliya.services.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FriendsController {

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
    @Autowired
    UserSearchController userSearchController = new UserSearchController();

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
        return userSearchController.showUserInfo(user_id, friends, currentUser, model);
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
        List<Friends> friends = friendsService.findOutgoingRequestsByUserId(user.getUserID());
        List<Friends> friends1 = friendsService.findIncomingRequestsByUserId(user.getUserID());
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
        List<Friends> friends = friendsService.findIncomingRequestsByUserId(user.getUserID());
        List<Person> personList = new ArrayList<>();
        for (Friends friend : friends) {
            User user1 = userService.findUserByUserID(friend.getUserID());
            if (!idUsers.contains(user1.getUserID())) {
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
        List<Friends> friends = friendsService.findOutgoingRequestsByUserId(user.getUserID());
        List<Person> personList = new ArrayList<>();
        for (Friends friend : friends) {
            User user1 = userService.findUserByUserID(friend.getUser2ID());
            if (!idUsers.contains(user1.getUserID())) {
                personList.add(new Person(user1, friend));
            }
        }
        model.addAttribute("personList", personList);
        return "friends";
    }
}

