package uz.pdp.task1.mapper.map;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import org.springframework.stereotype.Component;
import uz.pdp.task1.entity.Customer;
import uz.pdp.task1.mapper.BaseMapper;
import uz.pdp.task1.payload.CustomerCreateDto;
import uz.pdp.task1.payload.CustomerDto;
import uz.pdp.task1.payload.CustomerUpdateDto;


@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,componentModel = "spring")
@Component
public interface CustomerMapper extends BaseMapper<Customer,CustomerDto,CustomerCreateDto,CustomerUpdateDto> {


}
