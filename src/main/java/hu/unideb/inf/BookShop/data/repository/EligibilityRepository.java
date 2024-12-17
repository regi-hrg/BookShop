package hu.unideb.inf.BookShop.data.repository;

import hu.unideb.inf.BookShop.data.entity.EligibilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EligibilityRepository extends JpaRepository<EligibilityEntity, Long> {
}
