package pizza.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizza.delivery.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
