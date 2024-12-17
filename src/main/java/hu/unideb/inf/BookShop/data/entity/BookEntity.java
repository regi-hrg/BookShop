package hu.unideb.inf.BookShop.data.entity;

import jakarta.persistence.*;

import java.util.Objects;

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
    private int publish_year;

    public BookEntity() {
    }

    public BookEntity(long id, String title, double price, String author, String category, int publish_year) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
        this.category = category;
        this.publish_year = publish_year;
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

    public int getPublish_year() {
        return publish_year;
    }

    public void setPublish_year(int publish_year) {
        this.publish_year = publish_year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return id == that.id && Double.compare(price, that.price) == 0 && publish_year == that.publish_year && Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, author, category, publish_year);
    }
}
