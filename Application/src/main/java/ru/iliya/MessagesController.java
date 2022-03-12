package ru.iliya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.iliya.entities.Message;
import ru.iliya.entities.User;
import ru.iliya.services.MessageService;

import javax.print.Doc;
import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MessagesController {

    @Autowired
    MessageService messageService;

    public class LastMessage {
        public User user;
        public String message;
        public String ownerId;

        public LastMessage(User user, String message, String ownerId) {
            this.user = user;
            this.message = message;
            this.ownerId = ownerId;
        }
    }

    public class OwnerDialog {
        public String ownerId;
        public List<String> messages;

        public OwnerDialog(String ownerId, List<String> messages) {
            this.ownerId = ownerId;
            this.messages = messages;
        }
    }

    @GetMapping("/user/dialogs")
    public String showAllDialogsForUser(@RequestParam(name = "user", required = false, defaultValue = "1") String userId,
                                   Model model) {
        List<User> users = messageService.getDialogsForUser(userId);
        List<LastMessage> lastMessages = new ArrayList<>();
        for (User user1 : users) {
            String message = messageService.getLastMessage(userId, user1);
            lastMessages.add(new LastMessage(user1, message, userId));
        }
        model.addAttribute("lastMessages", lastMessages);
        return "all-dialogs-for-user";
    }

    @GetMapping("/user/chat/{owner}/{person}")
    public String showMessagesForUser(@PathVariable(name = "owner") String owner,
                                      @PathVariable(name = "person") String person,
                                      Model model) {
        List<String> messages = messageService.getAllMessagesForDialog(owner, person);
        model.addAttribute("ownerDialog", new OwnerDialog(owner, messages));
        return "p2p-dialog";
    }
}
