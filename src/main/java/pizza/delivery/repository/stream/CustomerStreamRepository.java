package pizza.delivery.repository.stream;

import pizza.delivery.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CustomerStreamRepository {
    private List<Customer> customerList = new ArrayList<>();

    private Long lastCustomerId = 0L;

    private Long generateId(){
        ++lastCustomerId;
        return lastCustomerId;
    }

    public Customer save(final Customer customer){
        customer.setId(generateId());
        customerList.add(customer);
        return customer;
    }

    public List<Customer> findAll(){
        return customerList;
    }

    //TODO: Customer update

    public Optional<Customer> findById(final Long id) {
        return customerList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public void deleteById(final Long id){
        customerList = customerList.stream()
                .filter(e -> e.getId().equals(id))
                .collect(Collectors.toList());
    }
}
