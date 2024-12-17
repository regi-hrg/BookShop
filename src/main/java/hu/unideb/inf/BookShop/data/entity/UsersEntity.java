package hu.unideb.inf.BookShop.data.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "users")
    private Set<OrderEntity> orders = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "users_eligibility",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "eligibility_id")
    )
    private Set<EligibilityEntity> eligibilities = new HashSet<>();

    public UsersEntity() {}

    public UsersEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Set<OrderEntity> getOrders() { return orders; }
    public void setOrders(Set<OrderEntity> orders) { this.orders = orders; }
    public Set<EligibilityEntity> getEligibilities() { return eligibilities; }
    public void setEligibilities(Set<EligibilityEntity> eligibilities) { this.eligibilities = eligibilities; }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
