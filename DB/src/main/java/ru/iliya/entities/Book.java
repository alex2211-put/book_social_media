package ru.iliya.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {
    private int book_id;
    private String title;
    private Date year_release;
    private String genre;
    private String link_internet;
    private int age_restriction;
    private int number_pages;
    private String annotation;
    private List<Author> authors;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return book_id == book.book_id && age_restriction == book.age_restriction && number_pages == book.number_pages && title.equals(book.title) && year_release.equals(book.year_release) && Objects.equals(genre, book.genre) && link_internet.equals(book.link_internet) && Objects.equals(annotation, book.annotation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id, title, year_release, genre, link_internet, age_restriction, number_pages, annotation, number_pages);
    }


    @Id
    @Column(name = "book_id", unique = true)
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Column(name = "year_release")
    public Date getYear_release() {
        return year_release;
    }

    public void setYear_release(Date year_release) {
        this.year_release = year_release;
    }

    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Books_Authors", joinColumns = {@JoinColumn(name = "book_id", updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id", nullable = false, updatable = false)})
    public List<Author> getAuthors() {
        return authors;
    }
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", year_release=" + year_release +
                ", genre='" + genre + '\'' +
                ", link_internet='" + link_internet + '\'' +
                ", age_restriction=" + age_restriction +
                ", number_pages=" + number_pages +
                ", annotation='" + annotation + '\'' +
                ", authors=" + authors +
                '}';
    }

    @Column(name = "link_internet")
    public String getLink_internet() {
        return link_internet;
    }

    public void setLink_internet(String link_internet) {
        this.link_internet = link_internet;
    }

    @Column(name = "age_restriction")
    public int getAge_restriction() {
        return age_restriction;
    }

    public void setAge_restriction(int age_restriction) {
        this.age_restriction = age_restriction;
    }

    @Column(name = "number_pages")
    public int getNumber_pages() {
        return number_pages;
    }

    public void setNumber_pages(int number_pages) {
        this.number_pages = number_pages;
    }

    @Column(name = "annotation")
    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

}

