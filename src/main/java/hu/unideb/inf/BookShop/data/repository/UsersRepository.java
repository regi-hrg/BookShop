package hu.unideb.inf.BookShop.data.repository;

import hu.unideb.inf.BookShop.data.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByEmail(String email);
}
