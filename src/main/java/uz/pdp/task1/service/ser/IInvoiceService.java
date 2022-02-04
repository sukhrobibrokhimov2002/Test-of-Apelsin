package uz.pdp.task1.service.ser;

import org.springframework.http.ResponseEntity;
import uz.pdp.task1.entity.Invoice;
import uz.pdp.task1.payload.InvoiceCreateDto;
import uz.pdp.task1.payload.InvoiceDto;
import uz.pdp.task1.payload.InvoiceUpdateDto;
import uz.pdp.task1.payload.response.ExpiredInvoiceDto;
import uz.pdp.task1.payload.response.WrongInvoiceDto;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.IGenericCrudService;

import java.util.List;

public interface IInvoiceService extends IGenericCrudService
        <Invoice, InvoiceDto, InvoiceCreateDto, InvoiceUpdateDto,Long> {

    public ResponseEntity<DataDto<List<ExpiredInvoiceDto>>> getExpiredInvoice();

    public ResponseEntity<DataDto<List<WrongInvoiceDto>>> getWrongInvoiceDto();
}
