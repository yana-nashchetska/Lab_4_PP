package pizza.delivery.resource;

import pizza.delivery.dto.CustomerDTO;
import pizza.delivery.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerResource {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public CustomerDTO findById(final @PathVariable Long id){
        return customerService.findDTOById(id);
    }

    @GetMapping
    public List<CustomerDTO> findAll(){
        return customerService.findAll();
    }

    @PostMapping
    public CustomerDTO createUser(final @RequestBody @Valid CustomerDTO customerDTO){
        return customerService.save(customerDTO);
    }

    @PutMapping
    public CustomerDTO updateUser(final @RequestBody CustomerDTO user){
        return customerService.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(final @PathVariable Long id){
        customerService.deleteById(id);
    }

    @GetMapping("/search")
    public CustomerDTO find(final @RequestParam String firstName,final @RequestParam String lastName){
        return customerService.searchByNameAndSurname(firstName, lastName);
    }
}
