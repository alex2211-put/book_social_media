package ru.iliya.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {
    private Integer bookID;
    private String title;
    private String year_release;
    private String genre;
    private String link_internet;
    private String age_restriction;
    private int number_pages;
    private String annotation;
    private List<Author> authors;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookID, book.bookID) && ageRestriction == book.ageRestriction && numberPages == book.numberPages && title.equals(book.title) && yearRelease.equals(book.yearRelease) && Objects.equals(genre, book.genre) && linkInternet.equals(book.linkInternet) && Objects.equals(annotation, book.annotation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID, title, yearRelease, genre, linkInternet, ageRestriction, numberPages, annotation, numberPages);
    }


    @Id
    @Column(name = "book_id", unique = true)
    public int getBookID() { return bookID; }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    @Column(name = "year_release")
    public String getYear_release() {
        return year_release;
    }

    public void setYear_release(String year_release) {
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
            inverseJoinColumns = {@JoinColumn(name = "author_id", nullable = false, updatable = false)})
    public List<Author> getAuthors() {
        return authors;
    }
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", yearRelease=" + yearRelease +
                ", genre='" + genre + '\'' +
                ", linkInternet='" + linkInternet + '\'' +
                ", ageRestriction=" + ageRestriction +
                ", numberPages=" + numberPages +
                ", annotation='" + annotation + '\'' +
                ", authors=" + authors +
                '}';
    }

    @Column(name = "link_internet")
    public String getLinkInternet() {
        return linkInternet;
    }
    public void setLinkInternet(String linkInternet) {
        this.linkInternet = linkInternet;
    }

    @Column(name = "age_restriction")
    public String getAge_restriction() {
        return age_restriction;
    }

    public void setAge_restriction(String age_restriction) {
        this.age_restriction = age_restriction;
    }

    @Column(name = "number_pages")
    public int getNumberPages() {
        return numberPages;
    }
    public void setNumberPages(int number_pages) {
        this.numberPages = number_pages;
    }

    @Column(name = "annotation")
    public String getAnnotation() {
        return annotation;
    }
    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

}

