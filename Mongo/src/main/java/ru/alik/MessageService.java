package ru.alik;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageService implements CommandLineRunner {
    @Autowired
    private MongoRep repository;

    public static void main(String[] args) {
        SpringApplication.run(MessageService.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
        repository.save(new Message("123", "456", "Привет"));
        repository.save(new Message("234", "567", "Как дела"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Message message : repository.findAll()) {
            System.out.println(message);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findById("123_456"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");

        }
}
