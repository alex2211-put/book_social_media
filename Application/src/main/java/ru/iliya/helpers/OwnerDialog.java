package ru.iliya.helpers;

import ru.iliya.entities.Message;

import java.util.List;

public class OwnerDialog {
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
