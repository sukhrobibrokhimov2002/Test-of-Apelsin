package uz.pdp.task1.payload;

import lombok.*;
import uz.pdp.task1.payload.temp.GenericDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailsDto extends GenericDto {

    private Double amount;
    private Integer quantity;
    private Long orderId;
    private Long productId;
}
