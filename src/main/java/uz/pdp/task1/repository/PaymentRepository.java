package uz.pdp.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.task1.entity.Detail;
import uz.pdp.task1.entity.Payment;
import uz.pdp.task1.payload.response.PaymentOverpaidDto;

import java.util.List;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "select sum(amount),invoice_id from payment " +
            "where invoice_id in(select distinct id from invoice) " +
            "group by invoice_id", nativeQuery = true)
    List<PaymentOverpaidDto> getOverpaidInvoice();
}
