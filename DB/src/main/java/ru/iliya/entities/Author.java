package ru.iliya.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "actors")
public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String country;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getActorId() {
        return this.id;
    }

    public void setActorId(int id) {
        this.id = id;
    }

    @Column(name = "firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    @Column(name = "lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", country='" + country + '\'' +
                '}';
    }

    @Column(name = "birthdate")
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getBirthdate() {
        return this.birthdate;
    }

    @Column(name = "country")
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }
}

