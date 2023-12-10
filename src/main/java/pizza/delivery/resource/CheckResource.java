package pizza.delivery.resource;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizza.delivery.dto.CheckDTO;
import pizza.delivery.service.CheckService;

import java.util.List;

@RestController
@RequestMapping("/api/checks")
@AllArgsConstructor
public class CheckResource {
    @Autowired
    private CheckService checkService;

    @GetMapping("/{id}")
    public ResponseEntity<CheckDTO> getCheckById(@PathVariable Long id) {
        CheckDTO checkDTO = checkService.getCheckById(id);
        return ResponseEntity.ok(checkDTO);
    }

    @GetMapping
    public ResponseEntity<List<CheckDTO>> getAllChecks() {
        List<CheckDTO> checks = checkService.getAllChecks();
        return ResponseEntity.ok(checks);
    }

    @PostMapping
    public ResponseEntity<CheckDTO> createCheck(@RequestBody @Valid CheckDTO checkDTO) {
        CheckDTO createdCheck = checkService.createCheck(checkDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCheck);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CheckDTO> updateCheck(@PathVariable Long id, @RequestBody @Valid CheckDTO checkDTO) {
        CheckDTO updatedCheck = checkService.updateCheck(id, checkDTO);
        return ResponseEntity.ok(updatedCheck);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheck(@PathVariable Long id) {
        checkService.deleteCheck(id);
        return ResponseEntity.noContent().build();
    }
}