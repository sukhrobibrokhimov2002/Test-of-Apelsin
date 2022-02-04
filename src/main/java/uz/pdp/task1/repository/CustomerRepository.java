package uz.pdp.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.task1.entity.Customer;
import uz.pdp.task1.payload.response.CustomerResponse;
import uz.pdp.task1.payload.response.OrdersByCountry;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByPhone(String phone);

    boolean existsByPhoneAndIdNot(String phone, Long id);


    Optional<Customer> findByPhone(String phone);

    @Query(value = "select * from customer cs where cs.id\n" +
            "not in(select customer_id from orders where date>'2016-01-01' and date<'2016-12-31')", nativeQuery = true)
    List<Customer> getCustomerWhoNotMakeOrder();

    @Query(value = "select  cs.name,cs.country,cs.phone, cast( max(ord.date) as varchar ) as dates from" +
            " orders ord join customer cs on cs.id=ord.customer_id " +
            "where ord.customer_id in(select distinct customer_id from orders)  \n" +
            "group by ord.customer_id,cs.name,cs.id", nativeQuery = true)
    List<CustomerResponse> getCustomersWithLatestOrder();

//    @Query(value = "select ord.customer.name,ord.customer.country,ord.customer.phone,max(ord.date)  from Orders ord where ord.customer.id " +
//            "in(select distinct ord.customer.id from Orders ord) " +
//            "group by ord.customer.id,ord.customer.name,ord.id,ord.customer.country,ord.customer.phone")
//    List<CustomerResponse> getCustomersWithLatestOrder();

    @Query(value = "select count(ord.id) number_of_orders,country from customer " +
            "join orders ord on ord.customer_id=customer.id where date>'2016-01-01' " +
            "and date<'2016-12-31' group by country", nativeQuery = true)
    List<OrdersByCountry> getOrdersByCountry();


}
