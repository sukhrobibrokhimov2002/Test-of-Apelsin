package uz.pdp.task1.entity;

import lombok.*;
import uz.pdp.task1.entity.temp.AbstractTemplate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends AbstractTemplate {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String country;
    private String address;
    @Column(unique = true, nullable = false)
    private String phone;
}
