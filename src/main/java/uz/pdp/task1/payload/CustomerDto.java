package uz.pdp.task1.payload;

import lombok.*;
import uz.pdp.task1.payload.temp.GenericDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CustomerDto extends GenericDto {

    private String name;
    private String country;
    private String address;
    private String phone;


    @Builder(builderMethodName = "childBuilder")
    public CustomerDto(Long id,String name,String country,String address,String phone){
        super(id);
        this.name=name;
        this.country=country;
        this.address=address;
        this.phone=phone;
    }
}
