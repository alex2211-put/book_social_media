package ru.iliya;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import ru.iliya.repositories.MongoRepository;
import ru.iliya.repositories.MongoRepositoryImpl;

// TODO: назвать иначе

@SpringBootApplication
public class SpringApplicationData implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationData.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        MongoRepository mongoMessageRepository = new MongoRepositoryImpl();
//        Message message = new Message("Hi!", "456", "123", "2022");
//        mongoMessageRepository.insertMessage(message);
//
//        System.out.println("Messages found with findAll():");
//        System.out.println(mongoMessageRepository.getMessagesForCollection("123_456"));
        System.out.println("-------------------------------");
        System.out.println();
        mongoMessageRepository.addDialogsToMongoDB("1", "123");
        mongoMessageRepository.addDialogsToMongoDB("1", "234");
        mongoMessageRepository.addDialogsToMongoDB("1", "123");
        System.out.println("-------------------------------");
        System.out.println("Dialogs found for user 1:");
        System.out.println(mongoMessageRepository.getDialogsForCollection("1"));
//        // fetch an individual customer
//
        System.out.println("--------------------------------");
        System.out.println("Dialogs found for user 456:");
        System.out.println(mongoMessageRepository.getDialogsForCollection("456"));

    }
}
