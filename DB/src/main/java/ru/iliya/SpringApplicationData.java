package ru.iliya;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import ru.iliya.entities.Message;
import ru.iliya.repositories.AuthorRepository;
import ru.iliya.repositories.BaseRepository;
import ru.iliya.repositories.MongoMessageRepository;

// TODO: назвать иначе

@SpringBootApplication
public class SpringApplicationData implements CommandLineRunner {
    @Autowired
    private MongoMessageRepository repository;
    @Autowired
    BaseRepository baseRepository;

    @Autowired
    AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationData.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
        repository.save(new Message("123", "456", "Привет!"));
        repository.save(new Message("234", "567", "Как дела?"));

        // fetch all customers
        System.out.println("Messages found with findAll():");
        for (Message message : repository.findAll()) {
            System.out.println(message);
        }
        System.out.println("-------------------------------");
        System.out.println();
        // fetch an individual customer
        System.out.println("Message found with findById('123_456'):");
        System.out.println(repository.findById("123_456"));

        System.out.println("--------------------------------");

    }
}
