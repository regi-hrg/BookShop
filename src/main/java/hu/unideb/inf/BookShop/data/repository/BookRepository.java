package hu.unideb.inf.BookShop.data.repository;

import hu.unideb.inf.BookShop.data.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findAllByTitle(String title);
    List<BookEntity> findByTitleIgnoreCase(String title);
    List<BookEntity> findByAuthorIgnoreCase(String author);
    List<BookEntity> findByPublishYear(int publish_year);
    List<BookEntity> findByCategory(String category);
}
