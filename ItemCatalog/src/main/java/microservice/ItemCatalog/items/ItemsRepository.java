package microservice.ItemCatalog.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {
    Optional<Items> findItemsByItemCode(String itemCode);
    @Query(value = "SELECT `id` AS Id, `item_code` AS itemCode FROM items ORDER BY `item_code` DESC LIMIT 1", nativeQuery = true)
    Optional<getItemsData> findLastEntry();
    public interface getItemsData {
        Long getId();
        String getItemCode();
    }
}
