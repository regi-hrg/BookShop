package hu.unideb.inf.BookShop.service.dto;

import java.util.Date;
import java.util.Set;

public class OrderDto {
    private long id;
    private Date date;
    private String status;
    private Long userId;
    private Set<Long> bookIds;

    public OrderDto() {
    }

    public OrderDto(long id, Date date, String status, Long userId, Set<Long> bookIds) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.userId = userId;
        this.bookIds = bookIds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(Set<Long> bookIds) {
        this.bookIds = bookIds;
    }
}
