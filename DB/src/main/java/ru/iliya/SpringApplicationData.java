package ru.iliya;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
<<<<<<< HEAD
import ru.iliya.entities.Message;
import ru.iliya.repositories.AuthorRepository;
import ru.iliya.repositories.BaseRepository;
//import ru.iliya.repositories.MongoMessageRepository;
=======
import ru.iliya.repositories.MongoRepository;
import ru.iliya.repositories.MongoRepositoryImpl;
>>>>>>> 7ef1451c93590c6ce9565a460a90479a575f86f1

// TODO: назвать иначе

@SpringBootApplication
public class SpringApplicationData implements CommandLineRunner {
<<<<<<< HEAD
//    @Autowired
//    private MongoMessageRepository repository;
    @Autowired
    BaseRepository baseRepository;
=======
>>>>>>> 7ef1451c93590c6ce9565a460a90479a575f86f1


    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationData.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
<<<<<<< HEAD
//        repository.deleteAll();
//        repository.save(new Message("123", "456", "Привет"));
//        repository.save(new Message("234", "567", "Как дела?"));

        // fetch all customers
        System.out.println("Messages found with findAll():");
 //       for (Message message : repository.findAll()) {
//            System.out.println(message);
        }
//        System.out.println("-------------------------------");
//        System.out.println();
        // fetch an individual customer
//        System.out.println("Message found with findById('123_456'):");
//        System.out.println(repository.findById("123_456"));

//        System.out.println("--------------------------------");
=======
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
>>>>>>> 7ef1451c93590c6ce9565a460a90479a575f86f1

    }
//}
