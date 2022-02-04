package uz.pdp.task1.mapper.map;


import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.pdp.task1.entity.Orders;
import uz.pdp.task1.mapper.BaseMapper;
import uz.pdp.task1.payload.OrderCreateDto;
import uz.pdp.task1.payload.OrderDto;
import uz.pdp.task1.payload.OrderUpdateDto;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,componentModel = "spring")
@Component
public interface OrdersMapper extends BaseMapper<Orders, OrderDto, OrderCreateDto, OrderUpdateDto> {

    @Mapping(target = "customerId",source = "entity.customer.id")
    @Override
    OrderDto toDto(Orders entity);
}
