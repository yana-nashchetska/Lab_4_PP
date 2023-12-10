package pizza.delivery.service.impl;

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
@RequiredArgsConstructor
public class PizzaOrderServiceImpl implements PizzaOrderService {
    private final PizzaOrderRepository pizzaOrderRepository;
    private final CustomerServiceImpl customerRepository;

    @Override
    public List<PizzaOrderDTO> findAll() {
        return pizzaOrderRepository.findAll().stream()
                .map(PizzaOrderDTO::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public void orderPizza(Long customerId, Long pizzaOrderId, String pizzaType) {

        if(customerRepository.findDTOById(customerId) == null){
            throw new BadRequestException(String.format("Customer with id {%s} not found", customerId));
        }

        if(pizzaOrderRepository.findById(pizzaOrderId).isEmpty()){
            throw new BadRequestException(String.format("PizzaOrder with id {%s} not found", pizzaOrderId));
        }

        if(pizzaType == null){
            throw new BadRequestException("PizzaType is not found");
        }

        CustomerDTO customerDTO = customerRepository.findDTOById(customerId);
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
        PizzaOrder pizzaOrder = pizzaOrderRepository.findById(orderId)
                .orElseThrow(() -> new BadRequestException(String.format("PizzaOrder with id {%s} not found", orderId)));

        if (!customer.getBasket().contains(pizzaOrder)) {
            throw new BadRequestException(String.format("PizzaOrder with id {%s} does not belong to Customer with id {%s}",
                    orderId, customerId));
        }

        pizzaOrder.setSauceType(sauceType);
        pizzaOrderRepository.save(pizzaOrder);
    }

    public void addCheeseCrust(Long customerId, Long orderId) {
        Customer customer = customerRepository.findById(customerId);
        PizzaOrder pizzaOrder = pizzaOrderRepository.findById(orderId)
                .orElseThrow(() -> new BadRequestException(String.format("PizzaOrder with id {%s} not found", orderId)));

        if (!customer.getBasket().contains(pizzaOrder)) {
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
        pizzaOrder.setWithCheeseCrust(pizzaOrderDTO.isWithCheeseCrust());
        pizzaOrder.setConfirmed(pizzaOrderDTO.isConfirmed());

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

