package uz.pdp.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.task1.entity.Category;
import uz.pdp.task1.entity.Customer;
import uz.pdp.task1.projection.CustomCategory;

import javax.validation.constraints.NotNull;

@RepositoryRestResource(path = "category", excerptProjection = CustomCategory.class)
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @RestResource(path = "byProductId")
    @Query(value = "select * from category where id=(select category_id from product where id=:productId)", nativeQuery = true)
    Category findCatByProductId(@Param("productId") Long productId);
}
