package microservice.ItemCatalog.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itemCode;
    @Column(length = 100, nullable = false)
    private String itemName;
    private String itemDescription;
    @Column(length = 40, nullable = false)
    private String createdBy;
    @Column(nullable = false)
    private Date createdTime;
}
