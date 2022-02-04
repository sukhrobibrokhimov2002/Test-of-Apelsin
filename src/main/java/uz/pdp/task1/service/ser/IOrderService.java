package uz.pdp.task1.service.ser;

import org.springframework.http.ResponseEntity;
import uz.pdp.task1.entity.Orders;
import uz.pdp.task1.payload.OrderCreateDto;
import uz.pdp.task1.payload.OrderDto;
import uz.pdp.task1.payload.OrderUpdateDto;
import uz.pdp.task1.payload.response.OrderWithoutInvoiceDto;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.IGenericCrudService;

import java.util.List;

public interface IOrderService extends IGenericCrudService
        <Orders, OrderDto, OrderCreateDto, OrderUpdateDto,Long> {
    public ResponseEntity<DataDto<List<OrderDto>>> getOrdersWithoutDetails();
    public  ResponseEntity<DataDto<List<OrderWithoutInvoiceDto>>> getOrderWithoutInvoice();
}
