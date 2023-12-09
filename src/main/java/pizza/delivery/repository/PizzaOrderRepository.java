package pizza.delivery.repository;

import pizza.delivery.entity.PizzaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaOrderRepository extends JpaRepository<PizzaOrder, Long> {
   // List<PizzaOrder> findAllByIsConfirmedFalse(); - мені здається,
    // цього методу нам не потрібно, його має зробити Настя
    // в сервісі чека

 /*   void savePizzaOrder(@RequestBody PizzaOrder pizzaOrder);
    void deleteById(final Long id);*/


    // в крупи цей інтерфейс є пустим, оскільки він
    // еквівалентний тікету.
}
