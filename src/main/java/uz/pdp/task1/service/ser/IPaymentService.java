package uz.pdp.task1.service.ser;

import org.springframework.http.ResponseEntity;
import uz.pdp.task1.entity.Payment;
import uz.pdp.task1.payload.PaymentCreateDto;
import uz.pdp.task1.payload.PaymentDto;
import uz.pdp.task1.payload.PaymentUpdateDto;
import uz.pdp.task1.payload.response.PaymentOverpaidDto;
import uz.pdp.task1.payload.response.PaymentOverpaidResponse;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.IGenericCrudService;

import javax.xml.crypto.Data;
import java.util.List;

public interface IPaymentService extends IGenericCrudService
        <Payment, PaymentDto, PaymentCreateDto, PaymentUpdateDto,Long> {
    public ResponseEntity<DataDto<List<PaymentOverpaidDto>>> getOverpaidInvoices();
}
