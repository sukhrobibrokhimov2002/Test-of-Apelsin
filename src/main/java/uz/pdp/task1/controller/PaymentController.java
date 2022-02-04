package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.payload.PaymentCreateDto;
import uz.pdp.task1.payload.PaymentDto;
import uz.pdp.task1.payload.response.PaymentOverpaidDto;
import uz.pdp.task1.payload.response.Result;
import uz.pdp.task1.payload.response.Result3;
import uz.pdp.task1.payload.temp.GenericDto;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.ser.impl.PaymentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentServiceImpl paymentService;


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody PaymentCreateDto paymentDto) {
        ResponseEntity<DataDto<GenericDto>> dataDtoResponseEntity = paymentService.create(paymentDto);
        return dataDtoResponseEntity;
    }


    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        ResponseEntity<DataDto<List<PaymentDto>>> all = paymentService.getAll();
        return all;
    }


    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> getOneById(@PathVariable Long id) {
        ResponseEntity<DataDto<PaymentDto>> dataDtoResponseEntity = paymentService.get(id);
        return dataDtoResponseEntity;

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ResponseEntity<DataDto<Boolean>> delete = paymentService.delete(id);
        return delete;
    }


    @GetMapping("/overpaid_invoices")
    public ResponseEntity<?> getOverPaid() {
        ResponseEntity<DataDto<List<PaymentOverpaidDto>>> overpaidInvoices =
                paymentService.getOverpaidInvoices();

        return overpaidInvoices;
    }

}

