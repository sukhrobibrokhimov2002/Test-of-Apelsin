package uz.pdp.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentUpdateDto {


    @NotNull
    private Long id;
    @NotNull
    private double amount;
    @NotNull
    private Integer invoiceId;
}
