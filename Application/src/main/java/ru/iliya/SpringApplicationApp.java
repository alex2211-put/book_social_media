package ru.iliya;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.iliya.entities.Book;
import ru.iliya.services.MessageService;


@SpringBootApplication
public class SpringApplicationApp {

    @Autowired
    SpringApplicationData springApplicationData;
    private String r;
    @Autowired
    MessageService messageService;


    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationApp.class, args);
    }

    @Bean
    public CommandLineRunner springdata() {
        return(args) -> {
            System.out.println(messageService.getDialogsForUser("1").get(0).get("user"));

            System.out.println("-------StartTest--------");


            System.out.println("----------end-----------");
        };
    }

}
