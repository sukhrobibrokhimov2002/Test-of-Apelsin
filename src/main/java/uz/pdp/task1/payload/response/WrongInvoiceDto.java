package uz.pdp.task1.payload.response;

import java.sql.Timestamp;

public interface WrongInvoiceDto {

    Integer getId();

    Timestamp getInvoice_issued();

    Integer getOrder_id();

    Timestamp getOrder_date();
}
