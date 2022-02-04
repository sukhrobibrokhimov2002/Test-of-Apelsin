package uz.pdp.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.task1.entity.Customer;
import uz.pdp.task1.entity.Detail;

import java.util.List;
import java.util.Optional;


public interface DetailRepository extends JpaRepository<Detail, Long> {
    boolean existsByOrder_Id(Long order_id);

    @Query("select det.id from Detail det where det.order.id=?1")
    List<Long> findDetailsIdByOrderId(Long id);

    @Query("select SUM(d.amount) from Detail d where d.order.id=?1")
    Double findSumOfAmount(Long order_id);
}
