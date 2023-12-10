package pizza.delivery.service.impl;

import jakarta.persistence.OneToMany;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizza.delivery.entity.Customer;
import pizza.delivery.dto.PizzaOrderDTO;
import pizza.delivery.entity.PizzaOrder;
import pizza.delivery.entity.SauceType;
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
    public void orderPizza(Long customerId, Long pizzaOrderId) {

        // FIXME: додати перевірку на наявність користувача та ордера - Є
        //FixMe: створити метод в репозиторії, який буде шукати користувача по ід

        // РОЗКОМЕНТУВАТИ, КОЛИ ДО КАСТОМЕРА БУДЕ ДОДАНО FIND BY ID!!!!!!!!!

 /*   Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new BadRequestException(String.format("Customer with id {%s} not found", customerId)));
    PizzaOrder pizzaOrder = pizzaOrderRepository.findById(pizzaOrderId)
            .orElseThrow(() -> new BadRequestException(String.format("PizzaOrder with id {%s} not found", pizzaOrderId)));
    customer.getBasket().add(pizzaOrder); // додаємо ордер в корзину*/
    }

    @Override
    public void addSauce(Long orderId, SauceType sauceType) {
        PizzaOrder pizzaOrder = pizzaOrderRepository.findById(orderId)
                .orElseThrow(() -> new BadRequestException(String.format("PizzaOrder with id {%s} not found", orderId)));
        pizzaOrder.setSauceType(sauceType);
        pizzaOrderRepository.save(pizzaOrder);
    }

    @Override
    public PizzaOrderDTO findDTOById(final Long id) {
        final PizzaOrder pizzaOrder = findById(id);

        return PizzaOrderDTO.toDTO(pizzaOrder);
    }

    private PizzaOrder findById(Long id) {
        return pizzaOrderRepository.findById(id).orElseThrow(() -> new BadRequestException(String.format("PizzaOrder with id {%s} not found", id)));
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

    //FIXME: переписати цей метод дя того, щоб можна було реалізовувати різну логіку видаляння

    /*@Override
    public void deleteById(Long id) {
        PizzaOrder pizzaOrder = findById(id);

        pizzaOrder.setConfirmed(Boolean.TRUE);
        pizzaOrderRepository.save(pizzaOrder);
    }*/


    @Override
    public void deleteFromBasket(Long orderId) {
        pizzaOrderRepository.deleteById(orderId);
    }


    // РОЗКОМЕНТУВАТИ ПІЗНІШЕ
@Override
    public void deleteAllFromBasket(Long customerId) {
     Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new BadRequestException(String.format("Customer with id {%s} not found", customerId)));
        customer.getBasket().clear();
        customerRepository.save(customer);
    }
}

