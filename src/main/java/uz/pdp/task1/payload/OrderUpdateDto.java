package uz.pdp.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateDto {
    @NotNull
    private Long orderId;
    private Long customerId;
    @NotNull
    private List<DetailsUpdateDto> detailsUpdateDtos;


}
