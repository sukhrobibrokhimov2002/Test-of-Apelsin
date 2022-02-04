package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.payload.InvoiceCreateDto;
import uz.pdp.task1.payload.InvoiceDto;
import uz.pdp.task1.payload.response.ExpiredInvoiceDto;
import uz.pdp.task1.payload.response.Result;
import uz.pdp.task1.payload.response.WrongInvoiceDto;
import uz.pdp.task1.payload.temp.GenericDto;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.ser.impl.InvoiceServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceServiceImpl invoiceService;


    @PostMapping("/add-invoice")
    public ResponseEntity<?> add(@RequestBody InvoiceCreateDto invoiceDto) {
        ResponseEntity<DataDto<GenericDto>> dataDtoResponseEntity = invoiceService.create(invoiceDto);
        return dataDtoResponseEntity;
    }


    @GetMapping("get-all")
    public ResponseEntity<?> getAll() {
        ResponseEntity<DataDto<List<InvoiceDto>>> all = invoiceService.getAll();
        return all;
    }


    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> getOneById(@PathVariable Long id) {
        ResponseEntity<DataDto<InvoiceDto>> dataDtoResponseEntity = invoiceService.get(id);
        return dataDtoResponseEntity;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        ResponseEntity<DataDto<Boolean>> delete = invoiceService.delete(id);
        return delete;
    }


    @GetMapping("/expired_invoices")
    public ResponseEntity<?> getExpiredInvoice() {
        ResponseEntity<DataDto<List<ExpiredInvoiceDto>>> expiredInvoice =
                invoiceService.getExpiredInvoice();

        return expiredInvoice;
    }


    @GetMapping("/wrong_date_invoices")
    public ResponseEntity<?> getWrongInvoice() {
        ResponseEntity<DataDto<List<WrongInvoiceDto>>> wrongInvoiceDto = invoiceService.getWrongInvoiceDto();
        return wrongInvoiceDto;
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
