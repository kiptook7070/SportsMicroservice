package microservice.ItemCatalog.utils.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class EntityResponse<T> {
    private String message;
    private T entity;
    private Integer statusCode;
}
