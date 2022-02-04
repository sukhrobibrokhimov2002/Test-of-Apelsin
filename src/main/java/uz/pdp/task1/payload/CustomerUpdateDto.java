package uz.pdp.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerUpdateDto {
    @NotNull(message = "Customer id must not be null")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String country;
    @NotNull
    private String address;
    @NotNull
    private String phone;
}
