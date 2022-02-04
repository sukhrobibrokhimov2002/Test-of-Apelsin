package uz.pdp.task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.task1.entity.Customer;
import uz.pdp.task1.entity.Invoice;
import uz.pdp.task1.payload.InvoiceDto;
import uz.pdp.task1.payload.response.ExpiredInvoiceDto;
import uz.pdp.task1.payload.response.WrongInvoiceDto;

import java.util.List;
import java.util.Optional;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    boolean existsByOrder_Id(Long order_id);

    Invoice findByOrder_Id(Long order_id);


    @Query(value = "select * from invoice where due<NOW()", nativeQuery = true)
    List<ExpiredInvoiceDto> getExpiredInvoice();

    @Query(value = "select inv.id,inv.issued invoice_issued,ord.id order_id,ord.date order_date from invoice inv join orders ord on ord.id=inv.order_id   \n" +
            "where inv.issued<ord.date", nativeQuery = true)
    public List<WrongInvoiceDto> getWrongInvoice();


}
