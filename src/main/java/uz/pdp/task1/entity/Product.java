package uz.pdp.task1.entity;

import lombok.*;
import uz.pdp.task1.entity.attachment.Attachment;
import uz.pdp.task1.entity.temp.AbstractTemplate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbstractTemplate {

    private String name;
    private String description;
    private double price;
    @ManyToOne
    private Category category;
    @OneToOne
    private Attachment attachment;
}
