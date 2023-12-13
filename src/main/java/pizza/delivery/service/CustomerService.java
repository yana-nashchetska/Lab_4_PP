package pizza.delivery.service;

import pizza.delivery.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO findDTOById(Long id);
    void deleteById(Long id);
    List<CustomerDTO> findAll();
    CustomerDTO save(CustomerDTO customer);
    CustomerDTO update(CustomerDTO customer);
    CustomerDTO searchByNameAndSurname(String firstName, String lastName);
}
