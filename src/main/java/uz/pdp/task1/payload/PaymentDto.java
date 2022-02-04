package uz.pdp.task1.payload;

import lombok.*;
import uz.pdp.task1.payload.temp.GenericDto;

import java.sql.Time;
import java.sql.Timestamp;
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto extends GenericDto {

    private Timestamp timestamp;
    private Double amount;
    private Long invoiceId;
}
