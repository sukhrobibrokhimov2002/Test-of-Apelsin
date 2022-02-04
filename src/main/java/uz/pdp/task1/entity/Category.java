package uz.pdp.task1.entity;

import lombok.*;
import uz.pdp.task1.entity.temp.AbstractTemplate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends AbstractTemplate {

    private String name;


}
