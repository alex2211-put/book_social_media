package ru.iliya.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.iliya.entities.Message;

import java.util.Optional;

public interface MongoRep extends MongoRepository<Message, String> {

    public Optional<Message> findById(String firstName);

}