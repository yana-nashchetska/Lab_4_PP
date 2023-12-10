package pizza.delivery.resource;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pizza.delivery.dto.CustomerDTO;
import pizza.delivery.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@Validated
public class CustomerResource {
    private final CustomerService customerService;

    @Autowired
    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        CustomerDTO responseBody = customerService.save(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable @Min(1) Long id) {
        CustomerDTO customerDTO = customerService.findDTOById(id);
        return ResponseEntity.ok(customerDTO);
    }

    @GetMapping("/all-users")
    public List<CustomerDTO> findAll(){
        return customerService.findAll();
    }

    @PutMapping
    public CustomerDTO updateUser(final @RequestBody @Valid CustomerDTO customerDTO, final @PathVariable Long id){
        return customerService.update(customerDTO);
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
