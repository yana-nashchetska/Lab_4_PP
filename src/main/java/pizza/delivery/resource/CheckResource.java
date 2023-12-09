package pizza.delivery.resource;

@RestController
@RequestMapping("/api/check")
@RequiredArgsConstructor
public class CheckResource {
    private final CheckService checkService;
}
