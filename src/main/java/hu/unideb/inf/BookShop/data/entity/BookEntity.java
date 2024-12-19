package hu.unideb.inf.BookShop.data.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private double price;

    @Column(name = "author")
    private String author;

    @Column(name = "category")
    private String category;

    @Column(name = "publish_year")
    private int publishYear;

    @Column(name = "creatorEmail")
    private String creatorEmail;

    @ManyToMany(mappedBy = "books")
    private Set<OrderEntity> orders = new HashSet<>();

    public BookEntity() {
    }

    public BookEntity(String title, double price, String author, String category, int publishYear, String creatorEmail) {
        this.title = title;
        this.price = price;
        this.author = author;
        this.category = category;
        this.publishYear = publishYear;
        this.creatorEmail = creatorEmail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
