package uz.pdp.task1.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


public interface CustomerResponse {

    String getName();

    String getPhone();


    String getCountry();

    String getDates();

}
