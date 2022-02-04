package uz.pdp.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerCreateDto {

    @NotNull(message = "Customer name must not be null")
    private String name;
    @NotNull(message = "Customer country must not be null")
    private String country;
    private String address;
    @NotNull(message = "Customer Phone must not be null")
    private String phone;
}
