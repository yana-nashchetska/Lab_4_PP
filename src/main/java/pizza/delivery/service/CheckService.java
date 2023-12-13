package pizza.delivery.service;

import pizza.delivery.dto.CheckDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CheckService {
    CheckDTO findDTOById(Long id);
    List<CheckDTO> findAll();
    CheckDTO createCheck(CheckDTO checkDTO);
    CheckDTO updateCheck(Long id, CheckDTO checkDTO);

    BigDecimal calculateTotalSum(Long id);

    void deleteCheck(Long id);

    CheckDTO getCheckById(Long id);

    List<CheckDTO> getAllChecks();
}
