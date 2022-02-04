package uz.pdp.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailsCreateDto {

    @NotNull
    private Integer quantity;
    private Long orderId;
    @NotNull
    private Long productId;
}
