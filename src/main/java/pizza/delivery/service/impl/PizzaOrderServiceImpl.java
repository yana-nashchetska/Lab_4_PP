package pizza.delivery.service.impl;

import jakarta.persistence.OneToMany;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pizza.delivery.dto.CustomerDTO;
import pizza.delivery.dto.PizzaOrderDTO;
import pizza.delivery.entity.PizzaOrder;
import pizza.delivery.exceptions.BadRequestException;
import pizza.delivery.repository.PizzaOrderRepository;
import pizza.delivery.service.PizzaOrderService;

import java.util.List;
import java.util.stream.Collectors;

//Пробую мімікувати код Крупи // топчик
@Service
@RequiredArgsConstructor
public class PizzaOrderServiceImpl implements PizzaOrderService {
    private final PizzaOrderRepository pizzaOrderRepository;
    // private final CustomerServiceImpl customerRepository;
// РОЗКОМЕНТУВАТИ КОД ВИЩЕ, КОЛИ БУДЕ ДОДАНО АНОТАЦІЮ СЕРВІСУ В КАСТОМЕРА
    @Override
    public List<PizzaOrderDTO> findAll() {
        return pizzaOrderRepository.findAll().stream()
                .map(PizzaOrderDTO::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public void orderPizza(Long customerId, Long pizzaOrderId) {
   // Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new BadRequestException(String.format("Customer with id {%s} not found", customerId)));
//РОЗКОМЕНТУВАТИ ПІЗНІШЕ
    }

    @Override
    public PizzaOrderDTO findDTOById(final Long id) {
        final PizzaOrder pizzaOrder = findById(id);

        return PizzaOrderDTO.toDTO(pizzaOrder);
    }

    private PizzaOrder findById(Long id) {
        return pizzaOrderRepository.findById(id).orElseThrow(() -> new BadRequestException(String.format("PizzaOrder with id {%s} not found", id)));
    }

    // мені здається, цей метод має зробити Настя, щоб перебирати ордери з полем true та додавати в чек

 /*   @Override
    // Зовсім не розумію чи це працює і як якщо воно працює
    public List<PizzaOrderDTO> findAll() {
        return pizzaOrderRepository.findAllByIsConfirmedFalse().stream().map(PizzaOrderDTO::toDTO).collect(Collectors.toList());
    }*/

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
    public void deleteById(Long id) {
        PizzaOrder pizzaOrder = findById(id);

        pizzaOrder.setConfirmed(Boolean.TRUE);
        pizzaOrderRepository.save(pizzaOrder);
    }

//    @Override
//    public void createOrder(Long id) {
//
//
//    }
//
//    @Override
//    public void saveOrder(Long customerDTOId, Long pizzaOrderDTOId){
//
//   }
}