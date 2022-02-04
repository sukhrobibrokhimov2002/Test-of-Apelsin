package uz.pdp.task1.payload;

import lombok.*;
import org.hibernate.criterion.Order;
import uz.pdp.task1.payload.temp.GenericDto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto extends GenericDto {

    private Long orderId;
    private Double amount;
    private Date issued;
    private Date due;



}
