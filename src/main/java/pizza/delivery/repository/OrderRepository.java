package pizza.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizza.delivery.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
