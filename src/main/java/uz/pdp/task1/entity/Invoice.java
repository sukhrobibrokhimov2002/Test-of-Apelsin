package uz.pdp.task1.entity;

import lombok.*;
import uz.pdp.task1.entity.temp.AbstractTemplate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice extends AbstractTemplate {

    @OneToOne(fetch = FetchType.LAZY)
    private Orders order;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private Date issued;
    @Column(nullable = false)
    private Date due;
    @OneToOne(fetch = FetchType.EAGER)
    private Orders orders;
}
