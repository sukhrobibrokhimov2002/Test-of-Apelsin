package uz.pdp.task1.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface PaymentOverpaidDto {

    double getSum();

    Integer getInvoice_id();

}
