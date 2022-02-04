package uz.pdp.task1.entity;

import lombok.*;
import uz.pdp.task1.entity.temp.AbstractTemplate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders extends AbstractTemplate {

    @Column(updatable = false,nullable = false)
    private Date date;
    @ManyToOne(optional = false)
    private Customer customer;


}
