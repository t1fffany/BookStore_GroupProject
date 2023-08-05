package com.company.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "book")
public class Book {
    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="isbn")
    private String isbn;

    @Column(name="publish_date")
    private LocalDate publishDate;

    @Column(name="author_id")
    private int authorId;

    @Column(name="title")
    private String title;

    @Column(name="publisher_id")
    private int publisherId;

    @Column(name="price")
    private BigDecimal price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publish_date) {
        this.publishDate = publish_date;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int author_id) {
        this.authorId = author_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisher_id) {
        this.publisherId = publisher_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && authorId == book.authorId && publisherId == book.publisherId && Objects.equals(isbn, book.isbn) && Objects.equals(publishDate, book.publishDate) && Objects.equals(title, book.title) && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, publishDate, authorId, title, publisherId, price);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", publishDate=" + publishDate +
                ", authorId=" + authorId +
                ", title='" + title + '\'' +
                ", publisherId=" + publisherId +
                ", price=" + price +
                '}';
    }
}
