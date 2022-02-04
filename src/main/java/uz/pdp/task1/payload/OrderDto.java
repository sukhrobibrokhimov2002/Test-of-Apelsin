package uz.pdp.task1.payload;

import lombok.*;
import uz.pdp.task1.payload.temp.GenericDto;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto extends GenericDto {

    private Date date;
    private Long customerId;
    private List<Long> detailsId;


}
