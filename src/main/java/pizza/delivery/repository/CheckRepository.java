package pizza.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizza.delivery.entity.Check;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface CheckRepository extends JpaRepository<Check, Long> {
    List<Check> findAllByOrders_PizzaType(String pizzaType);
    List<Check> findAllByTotalSumGreaterThan(BigDecimal amount);
    List<Check> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    //Виправіть назву цього методу якщо його треба буде використати
    //List<Check> findAllByOrdersAndCustomer_Id(Long customerId);
}
