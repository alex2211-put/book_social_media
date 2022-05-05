package ru.iliya.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.iliya.entities.Message;
import ru.iliya.entities.User;
import ru.iliya.helpers.LastMessage;
import ru.iliya.helpers.OwnerDialog;
import ru.iliya.security.SecurityUserConverter;
import ru.iliya.services.MessageServiceImpl;

import org.bson.Document;
import ru.iliya.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MessagesController {

    final
    MessageServiceImpl messageService;
    final
    SecurityUserConverter securityUserConverter;
    final
    UserService userService;

    public MessagesController(MessageServiceImpl messageService, SecurityUserConverter securityUserConverter, UserService userService) {
        this.messageService = messageService;
        this.securityUserConverter = securityUserConverter;
        this.userService = userService;
    }

    @RequestMapping("/user/dialogs")
    public String showAllDialogsForUserParam(@AuthenticationPrincipal UserDetails currentUser,
                                             Model model) {
        User user = securityUserConverter.getUserByDetails(currentUser);
        String userId = String.valueOf(user.getUserID());
        List<User> users = messageService.getDialogsForUser(String.valueOf(user.getUserID()));
        List<LastMessage> lastMessages = new ArrayList<>();
        for (User user1 : users) {
            String message = messageService.getLastMessage(userId, user1);
            LastMessage lastMessage = new LastMessage(user1, message, userId);
            lastMessages.add(lastMessage);
        }
        model.addAttribute("lastMessages", lastMessages);
        if (!lastMessages.isEmpty()) {
            return "all-dialogs-for-user";
        }
        return "no-dialogs-for-user";
    }

    @GetMapping("/user/chat/{person}")
    public String showMessagesForUser(@PathVariable(name = "person") String person,
                                      @AuthenticationPrincipal UserDetails currentUser,
                                      Model model) {
        User user = securityUserConverter.getUserByDetails(currentUser);
        String owner = String.valueOf(user.getUserID());
        User friend = userService.findUserByUserID(Integer.parseInt(person));
        List<Message> messages = messageService.getMessagesForPersons(person, owner);
        model.addAttribute(
                "ownerDialog",
                new OwnerDialog(owner, messages, person, user.getNickname(), friend.getNickname())
        );
        return "p2p-dialog";
    }

    @PostMapping("/user/chat/{person}/write")
    public String showAllDialogsForUser(@PathVariable(name = "person") String person,
                                        @RequestParam(name = "message") String message,
                                        @AuthenticationPrincipal UserDetails currentUser,
                                        Model model) {
        User user = securityUserConverter.getUserByDetails(currentUser);
        User friend = userService.findUserByUserID(Integer.parseInt(person));
        String owner = String.valueOf(user.getUserID());
        if (message.isEmpty())
            return "redirect:/user/chat/{person}";
        Message message1 = new Message(message, owner, person, "2022");
        messageService.writeToUser(message1);
        List<Message> messages = messageService.getMessagesForPersons(person, owner);
        model.addAttribute(
                "ownerDialog",
                new OwnerDialog(owner, messages, person, user.getNickname(), friend.getNickname())
        );
        return "redirect:/user/chat/{person}";
    }


}
