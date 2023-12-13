package pizza.delivery.exceptions;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponseDTO{
    private String message;
}
