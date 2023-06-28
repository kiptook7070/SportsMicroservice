package microservice.ItemCatalog.items;

import lombok.extern.slf4j.Slf4j;
import microservice.ItemCatalog.utils.response.EntityResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemsService {
    private final ItemsRepository itemsRepository;

    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public EntityResponse addItem(Items items) {
        try {
            EntityResponse response = new EntityResponse();
            Optional<Items> searchCode = itemsRepository.findItemsByItemCode(items.getItemCode());
            if (searchCode.isPresent()) {
                response.setMessage("Item with code " + items.getItemCode() + " Already Registered: !!");
                response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
            } else {
                String prefixCharacters = "ITC";
                String remainingFourDigits = "";
                Optional<ItemsRepository.getItemsData> itemsData = itemsRepository.findLastEntry();
                if (itemsData.isPresent()) {
                    String itemCode = itemsData.get().getItemCode();
                    String lastFourCharacters = itemCode.substring(itemCode.length() - 4);
                    Long lastFourDigits = Long.valueOf(lastFourCharacters);
                    String newCode = String.valueOf((lastFourDigits + 1));
                    do {
                        newCode = "0" + newCode;
                    } while (newCode.length() < 4);

                    remainingFourDigits = newCode;
                } else {
                    remainingFourDigits = "0001";
                }
                String generatedItemCode = prefixCharacters + remainingFourDigits;
                items.setItemCode(generatedItemCode);

                items.setCreatedBy("SYSTEM");
                items.setCreatedTime(new Date());
                Items addItem = itemsRepository.save(items);
                response.setMessage("Item With Code " + addItem.getItemCode() + " Created Successfully At " + addItem.getCreatedTime());
                response.setStatusCode(HttpStatus.CREATED.value());
                response.setEntity(addItem);
            }
            return response;
        } catch (Exception e) {
            log.info("Catched Error {} " + e);
            return null;
        }
    }

    public EntityResponse getItems() {
        try {
            EntityResponse response = new EntityResponse();
            List<Items> itemsList = itemsRepository.findAll();
            if (itemsList.size() > 0) {
                response.setMessage(itemsList.size() + " Already Created");
                response.setStatusCode(HttpStatus.FOUND.value());
                response.setEntity(itemsList);
            } else {
                response.setMessage("NO Single Item Added: Please Create Some items to consume this API URL !!");
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
            return response;
        } catch (Exception e) {
            log.info("Catched Error {} " + e);
            return null;
        }
    }
}
