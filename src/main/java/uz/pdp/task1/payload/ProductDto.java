package uz.pdp.task1.payload;

import lombok.*;
import uz.pdp.task1.entity.Category;
import uz.pdp.task1.payload.temp.GenericDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto  extends GenericDto {

    private String name;
    private String description;
    private double price;
    private Long categoryId;

}
