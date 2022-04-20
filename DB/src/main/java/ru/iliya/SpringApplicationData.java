package ru.iliya;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: назвать иначе

@SpringBootApplication
public class SpringApplicationData implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationData.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
    }
}