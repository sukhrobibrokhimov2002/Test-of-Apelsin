package uz.pdp.task1.payload.response;

import java.sql.Time;
import java.sql.Timestamp;

public interface ExpiredInvoiceDto {

    Integer getId();

    Double getAmount();

    Timestamp getDue();

    Timestamp getIssued();

    Integer getOrder_id();
}
