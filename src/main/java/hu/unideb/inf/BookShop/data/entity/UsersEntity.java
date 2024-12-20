package hu.unideb.inf.BookShop.data.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
public class UsersEntity  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "users")
    private Set<OrderEntity> orders = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_eligibility",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "eligibility_id")
    )
    private Set<EligibilityEntity> eligibilities = new HashSet<>();


    public UsersEntity() {
    }

    public UsersEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(EligibilityEntity j : eligibilities){
            authorities.add(new SimpleGrantedAuthority(j.getName()));
        }

        return authorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }

    public Set<EligibilityEntity> getEligibilities() {
        return eligibilities;
    }

    public void setEligibilities(Set<EligibilityEntity> eligibilities) {
        this.eligibilities = eligibilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity entity = (UsersEntity) o;
        return id == entity.id && Objects.equals(name, entity.name) && Objects.equals(email, entity.email) && Objects.equals(password, entity.password) && Objects.equals(orders, entity.orders) && Objects.equals(eligibilities, entity.eligibilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, orders, eligibilities);
    }
}
