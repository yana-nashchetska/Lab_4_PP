package pizza.delivery.resource;

import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import pizza.delivery.dto.CustomerDTO;
import pizza.delivery.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@Validated
public class CustomerResource {
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity createCustomer(final @RequestBody @Valid CustomerDTO customerDTO){
        final CustomerDTO responseBody = customerService.save(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
    @GetMapping("/{id}")
    public CustomerDTO findById(final @PathVariable @Min(1) Long id){
        return customerService.findDTOById(id);
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
