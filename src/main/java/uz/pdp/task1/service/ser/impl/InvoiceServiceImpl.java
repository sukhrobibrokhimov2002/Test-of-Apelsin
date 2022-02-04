package uz.pdp.task1.service.ser.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Detail;
import uz.pdp.task1.entity.Invoice;
import uz.pdp.task1.entity.Orders;
import uz.pdp.task1.mapper.GenericMapper;
import uz.pdp.task1.mapper.map.InvoiceMapper;
import uz.pdp.task1.payload.InvoiceCreateDto;
import uz.pdp.task1.payload.InvoiceDto;
import uz.pdp.task1.payload.InvoiceUpdateDto;
import uz.pdp.task1.payload.response.ExpiredInvoiceDto;
import uz.pdp.task1.payload.response.WrongInvoiceDto;
import uz.pdp.task1.payload.temp.GenericDto;
import uz.pdp.task1.repository.DetailRepository;
import uz.pdp.task1.repository.InvoiceRepository;
import uz.pdp.task1.repository.OrderRepository;
import uz.pdp.task1.response.AppErrorDto;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.GenericCrudService;
import uz.pdp.task1.service.ser.IInvoiceService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl extends GenericCrudService
        <Invoice, InvoiceDto, InvoiceCreateDto, InvoiceUpdateDto> implements IInvoiceService {


    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    InvoiceMapper invoiceMapper;
    @Autowired
    GenericMapper genericMapper;
    @Autowired
    DetailRepository detailRepository;


    @Override
    public ResponseEntity<DataDto<GenericDto>> create(InvoiceCreateDto createDto) {
        Optional<Orders> optionalOrders = orderRepository.findById(createDto.getOrderId());
        if (!optionalOrders.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "ORDER NOT FOUND")), HttpStatus.NOT_FOUND);
        Orders orders = optionalOrders.get();
       Invoice invoice=new Invoice();
        Double sumOfAmount = detailRepository.findSumOfAmount(orders.getId());

        invoice.setAmount(sumOfAmount);
        invoice.setOrder(orders);
        invoice.setIssued(new Date());
        invoice.setDue(new Date(System.currentTimeMillis() + 2592000000l));
        invoiceRepository.save(invoice);
        GenericDto genericDto = genericMapper.fromDomain(invoice);
        return new ResponseEntity<>(new DataDto<>(genericDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<InvoiceDto>> get(Long id) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (!optionalInvoice.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "INVOICE NOT FOUND")), HttpStatus.NOT_FOUND);

        Invoice optInvoice = optionalInvoice.get();
        InvoiceDto invoiceDto = invoiceMapper.toDto(optInvoice);
        return new ResponseEntity<>(new DataDto<>(invoiceDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<InvoiceDto>>> getAll() {
        List<Invoice> all = invoiceRepository.findAll();
        List<InvoiceDto> invoiceDtos = invoiceMapper.toDto(all);
        return new ResponseEntity<>(new DataDto<>(invoiceDtos), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<InvoiceDto>> update(InvoiceUpdateDto updateDto) {
        Optional<Orders> optionalOrders = orderRepository.findById(updateDto.getId());
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(updateDto.getId());

        if (!optionalOrders.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "ORDER NOT FOUND")), HttpStatus.NOT_FOUND);
        if (!optionalInvoice.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "Invoice NOT FOUND")), HttpStatus.NOT_FOUND);
        Orders optOrder = optionalOrders.get();
        Invoice optInvoice = optionalInvoice.get();

        Double sumOfAmount = detailRepository.findSumOfAmount(optOrder.getId());
        optInvoice.setAmount(sumOfAmount);
        optInvoice.setOrder(optOrder);
        invoiceRepository.save(optInvoice);
        InvoiceDto invoiceDto = invoiceMapper.toDto(optInvoice);
        return new ResponseEntity<>(new DataDto<>(invoiceDto), HttpStatus.OK);

    }


    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        try {
            invoiceRepository.deleteById(id);
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(false), HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<DataDto<List<ExpiredInvoiceDto>>> getExpiredInvoice() {
        List<ExpiredInvoiceDto> expiredInvoice = invoiceRepository.getExpiredInvoice();
        return new ResponseEntity<>(new DataDto<>(expiredInvoice), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<WrongInvoiceDto>>> getWrongInvoiceDto() {
        List<WrongInvoiceDto> wrongInvoice = invoiceRepository.getWrongInvoice();
        return new ResponseEntity<>(new DataDto<>(wrongInvoice), HttpStatus.OK);
    }
}
