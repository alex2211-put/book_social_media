package ru.iliya.helpers;

import ru.iliya.entities.Friends;
import ru.iliya.entities.User;

public class Person {
    public User user;
    public Friends friends;

    public Person(User user, Friends friends) {
        this.user = user;
        this.friends = friends;
    }
}