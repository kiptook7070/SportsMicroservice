package microservice.ItemCatalog.items;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/v3/api-docs/items")
public class ItemsController {
private final ItemsService itemsService;

    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addItem(@RequestBody Items items) {
        try {
            return ResponseEntity.ok().body(itemsService.addItem(items));
        } catch (Exception e) {
            log.info("Catched Error {} " + e);
            return null;
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> getItems() {
        try {
            return ResponseEntity.ok().body(itemsService.getItems());
        } catch (Exception e) {
            log.info("Catched Error {} " + e);
            return null;
        }
    }
}
