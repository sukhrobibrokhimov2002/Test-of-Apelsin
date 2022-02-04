package uz.pdp.task1.payload.response;

import java.sql.Date;
import java.sql.Timestamp;

public interface OrderWithoutInvoiceDto {

    Integer getId();

    Timestamp getDate();

    Double getTotal_price();
}
