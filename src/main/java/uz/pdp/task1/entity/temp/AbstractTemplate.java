package uz.pdp.task1.entity.temp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;


import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractTemplate {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;


    @Override
    public int hashCode() {
        return (getId() != null ? getId().intValue() : 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractTemplate)) {
            return false;
        }
        if (getId() == null) {
            return false;

        }

        AbstractTemplate unObject = (AbstractTemplate) o;

        String table1 = getEntityName(this);
        String table2 = getEntityName(unObject);
        return !(table1 == null || !table1.equals(table2)) && getId().equals(unObject.getId());
    }

    public static String getEntityName(AbstractTemplate o) {
        if (o instanceof HibernateProxy) {
            HibernateProxy proxy = (HibernateProxy) o;
            return proxy.getHibernateLazyInitializer().getEntityName();
        }
        Entity entity = o.getClass().getAnnotation(Entity.class);
        if (entity != null) {
            return !"".equals(entity.name()) ? entity.name() : o.getClass().getName();
        }
        return "";
    }




}
