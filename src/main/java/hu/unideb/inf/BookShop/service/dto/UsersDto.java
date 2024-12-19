package hu.unideb.inf.BookShop.service.dto;

import java.util.Objects;
public class UsersDto {
    private long id;
    private String name;
    private String email;
    private String password;

    public UsersDto() {
    }

    public UsersDto(long id, String name, String email, String password) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersDto usersDto = (UsersDto) o;
        return id == usersDto.id && Objects.equals(name, usersDto.name) && Objects.equals(email, usersDto.email) && Objects.equals(password, usersDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password);
    }
}
