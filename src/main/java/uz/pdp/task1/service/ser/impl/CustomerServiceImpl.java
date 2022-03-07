package uz.pdp.task1.service.ser.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Customer;
import uz.pdp.task1.mapper.GenericMapper;
import uz.pdp.task1.mapper.map.CustomerMapper;
import uz.pdp.task1.payload.CustomerCreateDto;
import uz.pdp.task1.payload.CustomerDto;
import uz.pdp.task1.payload.CustomerUpdateDto;
import uz.pdp.task1.payload.response.CustomerResponse;
import uz.pdp.task1.payload.response.OrdersByCountry;
import uz.pdp.task1.payload.temp.GenericDto;
import uz.pdp.task1.repository.CustomerRepository;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.GenericCrudService;
import uz.pdp.task1.service.ser.ICustomerService;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl extends GenericCrudService
        <Customer, CustomerDto, CustomerCreateDto, CustomerUpdateDto>
        implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    GenericMapper genericMapper;

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull CustomerCreateDto dto) {
        String phone = dto.getPhone();
        boolean existsByPhone = customerRepository.existsByPhone(phone);
        if (existsByPhone)
            return new ResponseEntity<>(new DataDto<>(false),HttpStatus.CONFLICT);

        Customer customer = customerMapper.fromCreateDto(dto);
        Customer save = customerRepository.save(customer);
        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(customer)), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<DataDto<CustomerDto>> get(Long id) {
        Optional<Customer> byId = customerRepository.findById(id);
        if (!byId.isPresent()) {
            return new ResponseEntity<>(new DataDto<>(null), HttpStatus.NOT_FOUND);
        }
        Customer customer = byId.get();
        return new ResponseEntity<>(new DataDto<>(customerMapper.toDto(customer)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<CustomerDto>> update(@NotNull CustomerUpdateDto updateDto) {
        Long id = updateDto.getId();
        Optional<Customer> byId = customerRepository.findById(id);
        if (!byId.isPresent()) {
            return new ResponseEntity<>(new DataDto<>(null), HttpStatus.NOT_FOUND);
        }
        Customer customerEntity = byId.get();
        boolean existsByPhoneAndIdNot = customerRepository.existsByPhoneAndIdNot(updateDto.getPhone(), updateDto.getId());
        if (existsByPhoneAndIdNot) {
            return new ResponseEntity<>(new DataDto<>(false), HttpStatus.CONFLICT);
        }
        Customer customer = customerMapper.fromUpdateDto(updateDto,customerEntity);
        customerRepository.save(customer);

        return get(customer.getId());
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        Optional<Customer> byId = customerRepository.findById(id);
        customerRepository.delete(byId.get());
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<CustomerDto>>> getAll() {
        List<Customer> customerRepositoryAll = customerRepository.findAll();
        List<CustomerDto> customerDtos = customerMapper.toDto(customerRepositoryAll);
        return new ResponseEntity<>(new DataDto<>(customerDtos), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<CustomerDto>>> getCustomerWhoNotMakeOrder() {
        List<Customer> customerWhoNotMakeOrder = customerRepository.getCustomerWhoNotMakeOrder();
        List<CustomerDto> customerDtos = customerMapper.toDto(customerWhoNotMakeOrder);
        return new ResponseEntity<>(new DataDto<>(customerDtos), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<CustomerResponse>>> getCustomerWithLatestOrder() {
        List<CustomerResponse> customersWithLatestOrder = customerRepository.getCustomersWithLatestOrder();

        return new ResponseEntity<>(new DataDto<>(customersWithLatestOrder), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<DataDto<List<OrdersByCountry>>> getOrdersByCountry() {
        List<OrdersByCountry> ordersByCountry = customerRepository.getOrdersByCountry();
        return new ResponseEntity<>(new DataDto<>(ordersByCountry
        ), HttpStatus.OK);

    }
}
