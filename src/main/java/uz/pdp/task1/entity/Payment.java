package uz.pdp.task1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import uz.pdp.task1.entity.temp.AbstractTemplate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

@Entity
public class Payment extends AbstractTemplate {

    @Column(nullable = false)
    private Timestamp timestamp;
    @Column(nullable = false)
    private Double amount;
    @ManyToOne
    @JsonIgnore
    private Invoice invoice;


}
