package pizza.delivery.service.impl;

import pizza.delivery.service.CustomerService;
import pizza.delivery.entity.Customer;
import pizza.delivery.repository.CustomerRepository;
import pizza.delivery.dto.CustomerDTO;
import pizza.delivery.exceptions.BadRequestException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerDTO findDTOById(final Long id) {
        final Customer customer = findById(id);

        return CustomerDTO.toDTO(customer);
    }

    private Customer findById(final Long id) {
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
    public CustomerDTO save(final CustomerDTO userDTO) {
        final Customer customer = new Customer();

        customer.setFirstName(customer.getFirstName());
        customer.setFirstName(customer.getLastName());
        customer.setIsAuthorized(customer.getIsAuthorized());
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
        return customerRepository.findOneByNameAndSurname(firstName, lastName)
                .map(CustomerDTO::toDTO)
                .orElseThrow(() -> new BadRequestException(String.format("User with name {%s} and surname {%s} not found", firstName, lastName)));
    }
}
