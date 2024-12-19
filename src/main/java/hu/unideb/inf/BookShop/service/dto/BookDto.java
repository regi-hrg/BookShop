package hu.unideb.inf.BookShop.service.dto;

import java.util.Objects;

public class BookDto {
    private long id;
    private String title;
    private double price;
    private String author;
    private String category;
    private int publishYear;
    private String creatorEmail;

    public BookDto() {
    }

    public BookDto(long id, String title, double price, String author, String category, int publishYear, String creatorEmail) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id ) &&
                Double.compare(price, bookDto.price) == 0 &&
                Objects.equals(publishYear, bookDto.publishYear) &&
                Objects.equals(title, bookDto.title) &&
                Objects.equals(author, bookDto.author) &&
                Objects.equals(category, bookDto.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, author, category, publishYear);
    }
}
