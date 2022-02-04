package uz.pdp.task1.service.ser.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Detail;
import uz.pdp.task1.entity.Orders;
import uz.pdp.task1.entity.Product;
import uz.pdp.task1.mapper.GenericMapper;
import uz.pdp.task1.mapper.map.DetailsMapper;
import uz.pdp.task1.payload.DetailsCreateDto;
import uz.pdp.task1.payload.DetailsDto;
import uz.pdp.task1.payload.DetailsUpdateDto;
import uz.pdp.task1.payload.temp.GenericDto;
import uz.pdp.task1.repository.DetailRepository;
import uz.pdp.task1.repository.OrderRepository;
import uz.pdp.task1.repository.ProductRepository;
import uz.pdp.task1.response.AppErrorDto;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.GenericCrudService;
import uz.pdp.task1.service.ser.IDetailsService;

import java.util.List;
import java.util.Optional;
@Service
public class DetailsServiceImpl extends GenericCrudService
        <Detail, DetailsDto, DetailsCreateDto, DetailsUpdateDto> implements IDetailsService {

    @Autowired
    DetailRepository detailRepository;
    @Autowired
    DetailsMapper detailsMapper;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    GenericMapper genericMapper;

    @Override
    public ResponseEntity<DataDto<DetailsDto>> get(Long id) {
        Optional<Detail> byId = detailRepository.findById(id);
        if (!byId.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "NOT FOUND")), HttpStatus.NOT_FOUND);

        Detail detail = byId.get();
        DetailsDto detailsDto = detailsMapper.toDto(detail);
        return new ResponseEntity<>(new DataDto<>(detailsDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<DetailsDto>>> getAll() {
        List<Detail> all = detailRepository.findAll();
        List<DetailsDto> detailsDtos = detailsMapper.toDto(all);
        return new ResponseEntity<>(new DataDto<>(detailsDtos), HttpStatus.OK);
    }


    public ResponseEntity<DataDto<Detail>> createDetails(DetailsCreateDto dto) {
        Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
        Optional<Orders> optionalOrders = orderRepository.findById(dto.getOrderId());
        if (!optionalProduct.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "PRODUCT NOT FOUND")), HttpStatus.NOT_FOUND);
        if (!optionalOrders.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "ORDER NOT FOUND")), HttpStatus.NOT_FOUND);
        Detail detail = detailsMapper.fromCreateDto(dto);
        detail.setOrder(optionalOrders.get());
        detail.setProduct(optionalProduct.get());
        detail.setAmount(dto.getQuantity() * optionalProduct.get().getPrice());
        Detail save = detailRepository.save(detail);

        return new ResponseEntity<>(new DataDto<>(save), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<DetailsDto>> update(DetailsUpdateDto dto) {
        Optional<Detail> optionalDetail = detailRepository.findById(dto.getId());
        Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
        Optional<Orders> optionalOrders = orderRepository.findById(dto.getOrderId());
        if (!optionalDetail.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "DETAIL NOT FOUND")), HttpStatus.NOT_FOUND);

        if (!optionalProduct.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "PRODUCT NOT FOUND")), HttpStatus.NOT_FOUND);
        if (!optionalOrders.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "ORDER NOT FOUND")), HttpStatus.NOT_FOUND);
        Detail optDetail = optionalDetail.get();
        Detail detail = detailsMapper.fromUpdateDto(dto, optDetail);
        detail.setAmount(detail.getQuantity() * optionalProduct.get().getPrice());
        detail.setOrder(optionalOrders.get());
        detail.setProduct(optionalProduct.get());
        detailRepository.save(detail);
        DetailsDto detailsDto = detailsMapper.toDto(detail);
        return new ResponseEntity<>(new DataDto<>(detailsDto), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        try {
            detailRepository.deleteById(id);
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(false), HttpStatus.OK);
        }

    }
}
