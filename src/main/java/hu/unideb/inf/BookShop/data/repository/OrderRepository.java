package hu.unideb.inf.BookShop.data.repository;

import hu.unideb.inf.BookShop.data.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
