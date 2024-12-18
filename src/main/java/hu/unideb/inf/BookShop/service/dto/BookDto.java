package hu.unideb.inf.BookShop.service.dto;

public class BookDto {
    private long id;
    private String title;
    private double price;
    private String author;
    private String category;
    private int publishYear;

    public BookDto() {
    }

    public BookDto(long id, String title, double price, String author, String category, int publishYear) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.author = author;
        this.category = category;
        this.publishYear = publishYear;
    }

    // Getters és Setters
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
}
