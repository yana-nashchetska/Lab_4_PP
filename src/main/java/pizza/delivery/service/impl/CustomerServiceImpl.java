package pizza.delivery.service.impl;

import lombok.AllArgsConstructor;
import pizza.delivery.entity.PizzaOrder;
import pizza.delivery.service.CustomerService;
import pizza.delivery.entity.Customer;
import pizza.delivery.repository.CustomerRepository;
import pizza.delivery.dto.CustomerDTO;
import pizza.delivery.exceptions.BadRequestException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO findDTOById(final Long id) {
        final Customer customer = findById(id);

        return CustomerDTO.toDTO(customer);
    }

    public Customer findById(final Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(String.format("User with id {%s} not found", id)));
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAllByIsActiveTrue().stream()
                .map(CustomerDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO save(final CustomerDTO customerDTO) {
        final Customer customer = new Customer();

        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setMoney(customerDTO.getMoney());
        List<PizzaOrder> tempBasket = new ArrayList<>();
        customer.getBasket().forEach(x->tempBasket.add(new PizzaOrder(x.getId(), x.getPizzaType(), x.getSauceType(), x.isWithCheeseCrust(), x.isConfirmed(), x.getPrice(), x.getCustomer(), x.getCheck())));
        customer.setBasket(tempBasket);
        customerRepository.save(customer);

        return CustomerDTO.toDTO(customer);
    }

    @Override
    public void deleteById(Long id) {
        Customer customer = findById(id);
        customer.setIsActive(Boolean.FALSE);
        customerRepository.save(customer);
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {

        if (customerDTO.getId() == null) {
            throw new BadRequestException("Id can't be null");
        }

        final Customer savedCustomer = findById(customerDTO.getId());
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        customerRepository.save(savedCustomer);

        return CustomerDTO.toDTO(savedCustomer);
    }

    @Override
    public CustomerDTO searchByNameAndSurname(String firstName, String lastName) {
        return customerRepository.findOneByFirstNameAndLastName(firstName, lastName)
                .map(CustomerDTO::toDTO)
                .orElseThrow(() -> new BadRequestException(String.format("User with name {%s} and surname {%s} not found",
                        firstName, lastName)));
    }
}
