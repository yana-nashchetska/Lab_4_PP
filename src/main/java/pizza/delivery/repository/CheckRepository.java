package pizza.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizza.delivery.entity.Check;

public interface CheckRepository extends JpaRepository<Check, Long> {
}
