package uz.pdp.task1.mapper.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.pdp.task1.entity.Payment;
import uz.pdp.task1.mapper.BaseMapper;
import uz.pdp.task1.payload.PaymentCreateDto;
import uz.pdp.task1.payload.PaymentDto;
import uz.pdp.task1.payload.PaymentUpdateDto;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,componentModel = "spring")
@Component
public interface PaymentMapper extends BaseMapper<Payment, PaymentDto, PaymentCreateDto, PaymentUpdateDto> {

//
//    @Mapping(target="timestamp",source = "entity.timestamp")
//    @Mapping(target="amount",source = "entity.amount")
    @Mapping(target="invoiceId",source = "entity.invoice.id")
    @Override
    PaymentDto toDto(Payment entity);
}
