package pizza.delivery.service.impl;

import org.springframework.stereotype.Service;
import pizza.delivery.dto.CheckDTO;
import pizza.delivery.entity.Check;
import pizza.delivery.entity.Order;
import pizza.delivery.entity.PizzaOrder;
import pizza.delivery.exceptions.BadRequestException;
import pizza.delivery.repository.CheckRepository;
import pizza.delivery.service.CheckService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {

    private final CheckRepository checkRepository;

    @Override
    public CheckDTO findDTOById(Long id) {
        Check check = findById(id);
        return CheckDTO.toDTO(check);
    }

    private Check findById(Long id) {
        return checkRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(String.format("Check with id {%s} not found", id)));
    }

    @Override
    public List<CheckDTO> findAll() {
        return checkRepository.findAll().stream()
                .map(CheckDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CheckDTO createCheck(CheckDTO checkDTO) {
        Check check = new Check();
        check.setDate(checkDTO.getDate());
        // Assuming that orders in CheckDTO are already populated correctly
        check.setOrders(checkDTO.getOrders());

        // Calculate total price based on the sum of product prices
        BigDecimal totalSum = calculateTotalSum(check.getOrders());
        check.setTotalPrice(totalSum);

        checkRepository.save(check);
        return CheckDTO.toDTO(check);
    }

    private BigDecimal calculateTotalSum(List<PizzaOrder> orders) {
        return null;
    }

    @Override
    public CheckDTO updateCheck(Long id, CheckDTO checkDTO) {
        if (checkDTO.getId() == null) {
            throw new BadRequestException("Id can't be null");
        }

        Check savedCheck = findById(id);

        savedCheck.setDate(checkDTO.getDate());
        savedCheck.setOrders(checkDTO.getOrders());

        // Calculate total price based on the sum of product prices
        BigDecimal totalSum = calculateTotalSum(savedCheck.getOrders());
        savedCheck.setTotalPrice(totalSum);

        checkRepository.save(savedCheck);
        return CheckDTO.toDTO(savedCheck);
    }

    @Override
    public BigDecimal calculateTotalSum(Long id) {
        Check check = findById(id);

        // Assuming Order class has a getPrice() method returning BigDecimal
        List<BigDecimal> orderPrices = check.getOrders().stream()
                .map(Order::getPrice)
                .collect(Collectors.toList());

        // Summing up the prices
        BigDecimal totalSum = orderPrices.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalSum;
    }


    @Override
    public void deleteCheck(Long id) {
        Check check = findById(id);
        checkRepository.delete(check);
    }
}
