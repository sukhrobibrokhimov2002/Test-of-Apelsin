package uz.pdp.task1.service.ser.impl;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Customer;
import uz.pdp.task1.entity.Detail;
import uz.pdp.task1.entity.Invoice;
import uz.pdp.task1.entity.Orders;
import uz.pdp.task1.mapper.GenericMapper;
import uz.pdp.task1.mapper.map.OrdersMapper;
import uz.pdp.task1.payload.*;
import uz.pdp.task1.payload.response.OrderWithoutInvoiceDto;
import uz.pdp.task1.payload.temp.GenericDto;
import uz.pdp.task1.repository.CustomerRepository;
import uz.pdp.task1.repository.DetailRepository;
import uz.pdp.task1.repository.InvoiceRepository;
import uz.pdp.task1.repository.OrderRepository;
import uz.pdp.task1.response.AppErrorDto;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.GenericCrudService;
import uz.pdp.task1.service.ser.IOrderService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl extends GenericCrudService
        <Orders, OrderDto, OrderCreateDto, OrderUpdateDto> implements IOrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    DetailRepository detailRepository;
    @Autowired
    InvoiceServiceImpl invoiceService;
    @Autowired
    DetailsServiceImpl detailsService;
    @Autowired
    GenericMapper genericMapper;
    @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    public ResponseEntity<DataDto<OrderDto>> get(Long id) {
        Optional<Orders> optionalOrders = orderRepository.findById(id);
        if (!optionalOrders.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "ORDER NOT FOUND")), HttpStatus.NOT_FOUND);

        Orders orders = optionalOrders.get();
        OrderDto orderDto = ordersMapper.toDto(orders);
        List<Long> detailsIdByOrderId = detailRepository.findDetailsIdByOrderId(orders.getId());
        orderDto.setDetailsId(detailsIdByOrderId);
        return new ResponseEntity<>(new DataDto<>(orderDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<OrderDto>>> getAll() {
        List<Orders> ordersList = orderRepository.findAll();
        List<OrderDto> orderDtos = ordersMapper.toDto(ordersList);
        for (OrderDto orderDto : orderDtos) {
            List<Long> byOrder_id = detailRepository.findDetailsIdByOrderId(orderDto.getId());
            orderDto.setDetailsId(byOrder_id);
        }



        return new ResponseEntity<>(new DataDto<>(orderDtos), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<DataDto<GenericDto>> create(OrderCreateDto orderCreateDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(orderCreateDto.getCustomerId());
        if (!optionalCustomer.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "CUSTOMER NOT FOUND")), HttpStatus.NOT_FOUND);
        Customer customer = optionalCustomer.get();
        Orders orders = new Orders();
        orders.setCustomer(customer);
        orders.setDate(new Date());
        Orders savedOrder = orderRepository.save(orders);


        for (DetailsCreateDto detailsCreateDto : orderCreateDto.getDetailsCreateDtos()) {
            detailsCreateDto.setOrderId(savedOrder.getId());
            detailsService.createDetails(detailsCreateDto);
        }
        invoiceService.create(new InvoiceCreateDto(savedOrder.getId()));

        GenericDto genericDto = genericMapper.fromDomain(orders);
        return new ResponseEntity<>(new DataDto<>(genericDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<OrderDto>> update(OrderUpdateDto orderUpdateDto) {
        Optional<Orders> optionalOrders = orderRepository.findById(orderUpdateDto.getOrderId());
        Optional<Customer> optionalCustomer = customerRepository.findById(orderUpdateDto.getCustomerId());

        if (!optionalOrders.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "ORDER NOT FOUND")), HttpStatus.NOT_FOUND);
        if (!optionalCustomer.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "ORDER NOT FOUND")), HttpStatus.NOT_FOUND);

        Orders orders = optionalOrders.get();
        orders.setCustomer(optionalCustomer.get());
        List<DetailsUpdateDto> detailsUpdateDtos = orderUpdateDto.getDetailsUpdateDtos();
        for (DetailsUpdateDto detailsUpdateDto : detailsUpdateDtos) {
            detailsUpdateDto.setOrderId(orders.getId());
            detailsService.update(detailsUpdateDto);
        }

        Invoice byOrder_id = invoiceRepository.findByOrder_Id(orders.getId());
        invoiceService.update(new InvoiceUpdateDto(byOrder_id.getId(), orders.getId()));

        OrderDto orderDto = ordersMapper.toDto(orders);
        return new ResponseEntity<>(new DataDto<>(orderDto), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        try {
            orderRepository.deleteById(id);
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(false), HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<DataDto<List<OrderDto>>> getOrdersWithoutDetails() {
        List<Orders> ordersWithoutDetails = orderRepository.findOrdersWithoutDetails();
        List<OrderDto> orderDtos = ordersMapper.toDto(ordersWithoutDetails);
        return new ResponseEntity<>(new DataDto<>(orderDtos), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<OrderWithoutInvoiceDto>>> getOrderWithoutInvoice() {
        List<OrderWithoutInvoiceDto> ordersWithoutInvoice = orderRepository.getOrdersWithoutInvoice();
        return new ResponseEntity<>(new DataDto<>(ordersWithoutInvoice), HttpStatus.OK);
    }
}
