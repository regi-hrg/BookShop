package hu.unideb.inf.BookShop.data.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "eligibility")
public class EligibilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "eligibilities")
    private Set<UsersEntity> users = new HashSet<>();

    public EligibilityEntity() {}

    public EligibilityEntity(String name) {
        this.name = name;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<UsersEntity> getUsers() { return users; }
    public void setUsers(Set<UsersEntity> users) { this.users = users; }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EligibilityEntity that = (EligibilityEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
