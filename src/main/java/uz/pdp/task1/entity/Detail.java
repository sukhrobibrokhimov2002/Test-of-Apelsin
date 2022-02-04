package uz.pdp.task1.entity;

import lombok.*;
import uz.pdp.task1.entity.temp.AbstractTemplate;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Detail extends AbstractTemplate {


    @ManyToOne(fetch = FetchType.LAZY)
    private Orders order;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @Column(nullable = false)
    private Integer quantity;
    private Double amount;

}
