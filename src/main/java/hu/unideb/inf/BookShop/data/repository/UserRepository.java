package hu.unideb.inf.BookShop.data.repository;

import hu.unideb.inf.BookShop.data.entity.BookEntity;
import hu.unideb.inf.BookShop.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserRepository, Long> {
    List<UserEntity> findByEmail(String email);
}
