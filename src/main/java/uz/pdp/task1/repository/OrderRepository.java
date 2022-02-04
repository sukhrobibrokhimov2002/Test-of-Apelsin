package uz.pdp.task1.repository;

import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.task1.entity.Orders;
import uz.pdp.task1.payload.response.OrderWithoutInvoiceDto;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Override
    Page<Orders> findAll(Pageable pageable);

    @Query(value = "select * from orders ord where ord.id not in  (select order_id from detail)\n" +
            "and ord.date<'2016-09-06';", nativeQuery = true)
    List<Orders> findOrdersWithoutDetails();

    @Query(value = "select tab.id,tab.date,tab.total_price from (select ord.id,ord.date," +
            "sum(d.quantity*pr.price) total_price from orders ord \n" +
            "  join detail d on d.order_id=ord.id join product pr on " +
            "d.product_id=pr.id group by ord.id) tab where tab.id not \n" +
            "  in(select order_id from invoice)", nativeQuery = true)
    List<OrderWithoutInvoiceDto> getOrdersWithoutInvoice();

}
