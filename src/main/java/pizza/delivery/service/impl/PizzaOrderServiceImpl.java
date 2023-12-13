package pizza.delivery.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizza.delivery.dto.CustomerDTO;
import pizza.delivery.entity.Customer;
import pizza.delivery.dto.PizzaOrderDTO;
import pizza.delivery.entity.PizzaOrder;
import pizza.delivery.exceptions.BadRequestException;
import pizza.delivery.repository.PizzaOrderRepository;
import pizza.delivery.service.PizzaOrderService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PizzaOrderServiceImpl implements PizzaOrderService {
    private PizzaOrderRepository pizzaOrderRepository;
    private CustomerServiceImpl customerRepository;

    @Override
    public List<PizzaOrderDTO> findAll() {
        return pizzaOrderRepository.findAll().stream()
                .map(PizzaOrderDTO::toDTO)
                .collect(Collectors.toList());

    }

    private boolean isPizzaOrderInCustomerBasket(Customer customer, PizzaOrder pizzaOrder) {
        return customer.getBasket().contains(pizzaOrder);
    }

    @Override
    public void orderPizza(Long customerId, String pizzaType) {
        CustomerDTO customerDTO = customerRepository.findDTOById(customerId);

        if (customerDTO == null || pizzaType == null) {
            throw new BadRequestException("Invalid customer or pizza type");
        }

        PizzaOrderDTO pizzaOrderDTO = new PizzaOrderDTO();
        pizzaOrderDTO.setPizzaType(pizzaType);
        pizzaOrderDTO.setConfirmed(false);
        pizzaOrderDTO.setWithCheeseCrust(false);

        customerDTO.getBasket().add(pizzaOrderDTO);
        customerRepository.save(customerDTO);
    }

    @Override
    public void addSauce(Long customerId, Long orderId, String sauceType) {
        Customer customer = customerRepository.findById(customerId);
        PizzaOrder pizzaOrder = findById(orderId);

        if (!isPizzaOrderInCustomerBasket(customer, pizzaOrder)) {
            throw new BadRequestException(String.format("PizzaOrder with id {%s} does not belong to Customer with id {%s}",
                    orderId, customerId));
        }

        pizzaOrder.setSauceType(sauceType);
        pizzaOrderRepository.save(pizzaOrder);
    }

    @Override
    public void addCheeseCrust(Long customerId, Long orderId) {
        Customer customer = customerRepository.findById(customerId);
        PizzaOrder pizzaOrder = findById(orderId);

        if (!isPizzaOrderInCustomerBasket(customer, pizzaOrder)) {
            throw new BadRequestException(String.format("PizzaOrder with id {%s} does not belong to Customer with id {%s}",
                    orderId, customerId));
        }

        pizzaOrder.setWithCheeseCrust(true);
        pizzaOrderRepository.save(pizzaOrder);
    }


    @Override
    public PizzaOrderDTO findDTOById(final Long id) {
        final PizzaOrder pizzaOrder = findById(id);
        return PizzaOrderDTO.toDTO(pizzaOrder);
    }

    private PizzaOrder findById(Long id) {
        return pizzaOrderRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(String.format("PizzaOrder with id {%s} not found", id)));
    }

    @Override
    public PizzaOrderDTO save(final PizzaOrderDTO pizzaOrderDTO) {
        final PizzaOrder pizzaOrder = new PizzaOrder();

        pizzaOrder.setPizzaType(pizzaOrderDTO.getPizzaType());
        pizzaOrder.setSauceType(pizzaOrderDTO.getSauceType());
        pizzaOrder.setWithCheeseCrust(pizzaOrderDTO.getWithCheeseCrust());
        pizzaOrder.setConfirmed(pizzaOrderDTO.getConfirmed());

        pizzaOrderRepository.save(pizzaOrder);

        return PizzaOrderDTO.toDTO(pizzaOrder);
    }

    @Override
    public void deleteFromBasket(Long orderId) {
        pizzaOrderRepository.deleteById(orderId);
    }
@Override
    public void deleteAllFromBasket(Long customerId) {
     Customer customer = customerRepository.findById(customerId);
     customer.getBasket().clear();

     customerRepository.save(CustomerDTO.toDTO(customer));
    }
}

