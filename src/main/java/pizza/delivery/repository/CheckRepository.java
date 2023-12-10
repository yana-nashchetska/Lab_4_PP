package pizza.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizza.delivery.entity.Check;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface CheckRepository extends JpaRepository<Check, Long> {
    List<Check> findAllByOrders_PizzaName(String pizzaType);
    List<Check> findAllByTotalPriceGreaterThan(BigDecimal amount);
    List<Check> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Check> findAllByOrders_CustomerId(Long customerId);
    //
}
