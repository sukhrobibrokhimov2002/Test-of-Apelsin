package uz.pdp.task1.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public interface HighDemandProductDto {
    Integer getNumberOfOrders();

    String getName();

    Integer getId();

}
