package uz.pdp.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.task1.entity.Category;
import uz.pdp.task1.entity.Detail;
import uz.pdp.task1.entity.Product;
import uz.pdp.task1.payload.response.HighDemandProductDto;
import uz.pdp.task1.payload.response.ProductInBulkResponse;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from category where id=(select category_id from product where id:=productId);", nativeQuery = true)
    Category findCatByProductId(Long productId);

    @Query(value = "select * from (select count(ord.id) numberOfOrders,pr.name,pr.id from orders ord join detail d on ord.id=d.order_id join product pr on pr.id=d.product_id group by pr.id,pr.name) t\n" +
            "where numberOfOrders>10;", nativeQuery = true)
    List<HighDemandProductDto> getHighDemandProducts();

    @Query(value = "select pr.id,name,price from product pr   where pr.id in (select product_id from detail where quantity>7 )"
            , nativeQuery = true)
    List<ProductInBulkResponse> getProductsInBulk();
}
