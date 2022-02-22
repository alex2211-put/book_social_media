package ru.iliya;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import ru.iliya.repositories.AuthorRepository;
import ru.iliya.repositories.BaseRepository;

// TODO: назвать иначе

@SpringBootApplication
public class SpringApplicationData {

    @Autowired
    BaseRepository baseRepository;

    @Autowired
    AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationData.class, args);
    }

}
