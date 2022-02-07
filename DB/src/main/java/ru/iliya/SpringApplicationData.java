package ru.iliya;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.iliya.repositories.AuthorRepository;
import ru.iliya.repositories.BaseRepository;
import ru.iliya.repositories.BookRepository;

import javax.validation.constraints.Email;

@SpringBootApplication
public class SpringApplicationData {

    @Autowired
    BaseRepository baseRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationData.class, args);
    }

}
