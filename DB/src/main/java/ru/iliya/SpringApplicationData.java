package ru.iliya;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import ru.iliya.entities.Author;
import ru.iliya.repositories.AuthorRepository;
import ru.iliya.repositories.BaseRepository;


@SpringBootApplication
public class SpringApplicationData {

    @Autowired
    BaseRepository baseRepository;

    @Autowired
    AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationData.class, args);
    }

    @Bean
    public CommandLineRunner springdata() {
        return(args) -> {
            Author authorA = new Author();
            authorA.setCountry("Russia");
            authorA.setFirstName("Alex");
            authorA.setLastName("Putin");

            Author authorB = new Author();
            authorB.setCountry("USA");
            authorB.setFirstName("Roma");
            authorB.setLastName("Putin");

            Author authorC = new Author();
            authorC.setCountry("Russia");
            authorC.setFirstName("Ilia");
            authorC.setLastName("O");

            authorRepository.save(authorA);
            authorRepository.save(authorB);
            authorRepository.save(authorC);

            System.out.println(authorRepository.findByLastNameLike("%in"));
            System.out.println(authorRepository.findПлизByFirstNameLike("%l%"));

            System.out.println("-------end--------");
        };
    }

}
