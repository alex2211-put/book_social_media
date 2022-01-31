package ru.iliya;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:context.xml"})
public class SpringApplicationData {

    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationData.class, args);
    }

}
