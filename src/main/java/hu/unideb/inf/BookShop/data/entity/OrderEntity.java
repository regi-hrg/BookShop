package hu.unideb.inf.BookShop.data.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "date")
    private Date date;
    @Column(name = "status")
    private String status;

    @ManyToMany(mappedBy = "book")
    private Set<BookEntity> bookId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public OrderEntity() {
    }

    public OrderEntity(long id, Date date, String status) {
        this.id = id;
        this.date = date;
        this.status = status;
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

    public Set<BookEntity> getBookId() {
        return bookId;
    }

    public void setBookId(Set<BookEntity> bookId) {
        this.bookId = bookId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id == that.id && Objects.equals(date, that.date) && Objects.equals(status, that.status) && Objects.equals(bookId, that.bookId) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, status, bookId, user);
    }
}
