package uz.pdp.task1.payload;

import lombok.*;
import uz.pdp.task1.payload.temp.GenericDto;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceUpdateDto {

    @NotNull
    private Long id;
    @NotNull
    private Long orderId;




}
