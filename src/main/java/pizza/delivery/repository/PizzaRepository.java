package pizza.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizza.delivery.entity.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long>{

}
