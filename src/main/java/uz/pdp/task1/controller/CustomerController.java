package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.payload.CustomerCreateDto;
import uz.pdp.task1.payload.CustomerDto;
import uz.pdp.task1.payload.CustomerUpdateDto;
import uz.pdp.task1.payload.response.CustomerResponse;
import uz.pdp.task1.payload.response.OrdersByCountry;
import uz.pdp.task1.payload.temp.GenericDto;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.ser.impl.CustomerServiceImpl;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;


    @PostMapping("/add-customer")
    public ResponseEntity<DataDto<GenericDto>> add(@Valid @RequestBody CustomerCreateDto customerCreateDto) {
        ResponseEntity<DataDto<GenericDto>> dataDtoResponseEntity = customerService.create(customerCreateDto);
        return dataDtoResponseEntity;
    }


    @GetMapping("/get-all")
    public ResponseEntity<DataDto<List<CustomerDto>>> getAll() {
        ResponseEntity<DataDto<List<CustomerDto>>> all = customerService.getAll();
        return all;
    }


    @GetMapping("/get-one/{id}")
    public ResponseEntity<DataDto<CustomerDto>> getOneById(@PathVariable Long id) {
        ResponseEntity<DataDto<CustomerDto>> dataDtoResponseEntity = customerService.get(id);
        return dataDtoResponseEntity;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable Long id) {
        ResponseEntity<DataDto<Boolean>> delete = customerService.delete(id);
        return delete;
    }


    @PutMapping("/update")
    public ResponseEntity<DataDto<CustomerDto>> edit( @Valid @RequestBody CustomerUpdateDto customerUpdateDto) {
        ResponseEntity<DataDto<CustomerDto>> update = customerService.update(customerUpdateDto);
        return update;
    }


    @GetMapping("/customers_without_orders")
    public  ResponseEntity<DataDto<List<CustomerDto>>> getCusWithoutOrder( ) {
        ResponseEntity<DataDto<List<CustomerDto>>> customerWhoNotMakeOrder = customerService.getCustomerWhoNotMakeOrder();
        return customerWhoNotMakeOrder;
    }


    @GetMapping("/customers_last_orders")
    public ResponseEntity<DataDto<List<CustomerResponse>>> getCustomerWithLatestOrder() {
        ResponseEntity<DataDto<List<CustomerResponse>>> customerWithLatestOrder = customerService.getCustomerWithLatestOrder();
        return customerWithLatestOrder;
    }


    @GetMapping("/number_of_products_in_year")
    public ResponseEntity<DataDto<List<OrdersByCountry>>> getNumberOfOrdersByCountry() {
        ResponseEntity<DataDto<List<OrdersByCountry>>> ordersByCountry = customerService.getOrdersByCountry();
        return ordersByCountry;
    }

    //for displaying validation message on console
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
