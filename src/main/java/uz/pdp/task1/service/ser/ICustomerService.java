package uz.pdp.task1.service.ser;

import org.springframework.http.ResponseEntity;
import uz.pdp.task1.entity.Customer;
import uz.pdp.task1.payload.CustomerCreateDto;
import uz.pdp.task1.payload.CustomerDto;
import uz.pdp.task1.payload.CustomerUpdateDto;
import uz.pdp.task1.payload.response.CustomerResponse;
import uz.pdp.task1.payload.response.OrdersByCountry;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.IGenericCrudService;

import java.util.List;

public interface ICustomerService extends IGenericCrudService
        <Customer, CustomerDto, CustomerCreateDto, CustomerUpdateDto, Long> {


    public ResponseEntity<DataDto<List<CustomerDto>>> getCustomerWhoNotMakeOrder();
    public ResponseEntity<DataDto<List<CustomerResponse>>> getCustomerWithLatestOrder();
    public ResponseEntity<DataDto<List<OrdersByCountry>>> getOrdersByCountry();

}
