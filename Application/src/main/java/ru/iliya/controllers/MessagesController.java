package ru.iliya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.iliya.entities.Message;
import ru.iliya.entities.User;
import ru.iliya.security.SecurityUserConverter;
import ru.iliya.services.MessageServiceImpl;

import org.bson.Document;
import ru.iliya.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MessagesController {

    @Autowired
    MessageServiceImpl messageService;
    @Autowired
    SecurityUserConverter securityUserConverter;
    @Autowired
    UserService userService;

    public static class LastMessage {
        public User user;
        public String message;

        @Override
        public String toString() {
            return "LastMessage{" +
                    "user=" + user +
                    ", message='" + message + '\'' +
                    ", ownerId='" + ownerId + '\'' +
                    '}';
        }

        public String ownerId;

        public LastMessage(User user, String message, String ownerId) {
            this.user = user;
            this.message = message;
            this.ownerId = ownerId;
        }
    }

    public static class OwnerDialog {
        public String ownerId;
        public String partnerId;
        public List<Message> messages;
        public String ownerNick;
        public String partnerNick;

        public OwnerDialog(String ownerId, List<Message> messages, String partnerId, String ownerNick, String partnerNick) {
            this.ownerId = ownerId;
            this.ownerNick = ownerNick;
            this.messages = messages;
            this.partnerId = partnerId;
            this.partnerNick = partnerNick;
        }
    }

    @RequestMapping("/user/dialogs")
    public String showAllDialogsForUserParam(@AuthenticationPrincipal UserDetails currentUser,
                                             Model model) {
        User user = securityUserConverter.getUserByDetails(currentUser);
        String userId = String.valueOf(user.getUserID());
        List<User> users = messageService.getDialogsForUser(String.valueOf(user.getUserID()));
        System.out.println(users);
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

    private List<Message> messages2 = new ArrayList<>();
    String person = null;

    @GetMapping("/user/chat/{person}")
    public String showMessagesForUser(@PathVariable(name = "person") String person,
                                      @AuthenticationPrincipal UserDetails currentUser,
                                      Model model) {
        User user = securityUserConverter.getUserByDetails(currentUser);
        String owner = String.valueOf(user.getUserID());
        User friend = userService.findUserByUserID(Integer.parseInt(person));
        List<Document> messages = messageService.getAllMessagesForDialog(owner, person);
        if (this.person == null || !person.equals(owner)) {
            messages2 = new ArrayList<>();
            for (Document document : messages) {
                messages2.add(new Message(
                        document.get("text").toString(),
                        document.get("from").toString(),
                        document.get("to").toString(),
                        "2022"));
            }
            this.person = owner;
        }
        model.addAttribute("ownerDialog", new OwnerDialog(owner, messages2, person, user.getNickname(), friend.getNickname()));
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
        messages2.add(message1);
        model.addAttribute("ownerDialog", new OwnerDialog(owner, messages2, person, user.getNickname(), friend.getNickname()));
        return "redirect:/user/chat/{person}";
    }

}
