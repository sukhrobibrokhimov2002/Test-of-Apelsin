package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.payload.OrderCreateDto;
import uz.pdp.task1.payload.OrderDto;
import uz.pdp.task1.payload.response.OrderWithoutInvoiceDto;
import uz.pdp.task1.payload.response.Result;
import uz.pdp.task1.payload.temp.GenericDto;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.ser.impl.OrderServiceImpl;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;


    @PostMapping("add-order")
    public ResponseEntity<?> add(@Valid @RequestBody OrderCreateDto orderDto) {
        ResponseEntity<DataDto<GenericDto>> dataDtoResponseEntity =
                orderService.create(orderDto);
        return dataDtoResponseEntity;
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        ResponseEntity<DataDto<List<OrderDto>>> all = orderService.getAll();
        return all;
    }


    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> getOneById(@PathVariable Long id) {
        ResponseEntity<DataDto<OrderDto>> dataDtoResponseEntity = orderService.get(id);
        return dataDtoResponseEntity;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ResponseEntity<DataDto<Boolean>> delete = orderService.delete(id);
        return delete;

    }


    @GetMapping("/orders_without_details")
    public ResponseEntity<?> getOrdersWithoutDetails() {
        ResponseEntity<DataDto<List<OrderDto>>> ordersWithoutDetails = orderService.getOrdersWithoutDetails();
        return ordersWithoutDetails;
    }


    @GetMapping("/orders_without_invoices")
    public ResponseEntity<?> getOrdersWithoutInvoice() {
        ResponseEntity<DataDto<List<OrderWithoutInvoiceDto>>> orderWithoutInvoice = orderService.getOrderWithoutInvoice();
        return orderWithoutInvoice;
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
