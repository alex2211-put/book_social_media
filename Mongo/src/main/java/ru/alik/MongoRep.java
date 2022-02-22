package ru.alik;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoRep extends MongoRepository<Message, String> {

    public Optional<Message> findById(String firstName);

}