package uz.pdp.task1.service.ser.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Invoice;
import uz.pdp.task1.entity.Payment;
import uz.pdp.task1.mapper.GenericMapper;
import uz.pdp.task1.mapper.map.PaymentMapper;
import uz.pdp.task1.payload.PaymentCreateDto;
import uz.pdp.task1.payload.PaymentDto;
import uz.pdp.task1.payload.PaymentUpdateDto;
import uz.pdp.task1.payload.response.PaymentOverpaidDto;
import uz.pdp.task1.payload.temp.GenericDto;
import uz.pdp.task1.repository.InvoiceRepository;
import uz.pdp.task1.repository.PaymentRepository;
import uz.pdp.task1.response.AppErrorDto;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.GenericCrudService;
import uz.pdp.task1.service.ser.IPaymentService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class PaymentServiceImpl extends GenericCrudService
        <Payment, PaymentDto, PaymentCreateDto, PaymentUpdateDto> implements IPaymentService {

    @Autowired
    PaymentMapper paymentMapper;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    GenericMapper genericMapper;

    @Override
    public ResponseEntity<DataDto<PaymentDto>> get(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (!optionalPayment.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "Payment NOT FOUND")), HttpStatus.NOT_FOUND);
        Payment payment = optionalPayment.get();
        PaymentDto paymentDto = paymentMapper.toDto(payment);
        return new ResponseEntity<>(new DataDto<>(paymentDto), HttpStatus.OK);
    }


    public ResponseEntity<DataDto<List<PaymentDto>>> getAll() {
        List<Payment> all = paymentRepository.findAll();
        List<PaymentDto> paymentDtos = paymentMapper.toDto(all);
        return new ResponseEntity<>(new DataDto<>(paymentDtos), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<DataDto<GenericDto>> create(PaymentCreateDto dto) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(dto.getInvoiceId());
        if (!optionalInvoice.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "INVOICE NOT FOUND")), HttpStatus.NOT_FOUND);

        Invoice invoice = optionalInvoice.get();
        if (invoice.getDue().before(new Date()))
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(409, "INVOICE IS EXPIRED")), HttpStatus.CONFLICT);
        if (invoice.getAmount() >dto.getAmount())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(409, "NOT ENOUGH MONEY")), HttpStatus.CONFLICT);

        Payment payment = paymentMapper.fromCreateDto(dto);
        payment.setTimestamp(new Timestamp(System.currentTimeMillis()));
        payment.setInvoice(invoice);
        paymentRepository.save(payment);
        GenericDto genericDto = genericMapper.fromDomain(payment);
        return new ResponseEntity<>(new DataDto<>(genericDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        try {
            paymentRepository.deleteById(id);
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(false), HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<DataDto<List<PaymentOverpaidDto>>> getOverpaidInvoices() {
        List<PaymentOverpaidDto> overpaidInvoice = paymentRepository.getOverpaidInvoice();
        return new ResponseEntity<>(new DataDto<>(overpaidInvoice), HttpStatus.OK);
    }
}
