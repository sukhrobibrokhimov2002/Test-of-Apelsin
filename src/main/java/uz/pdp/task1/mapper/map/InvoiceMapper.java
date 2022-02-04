package uz.pdp.task1.mapper.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.pdp.task1.entity.Invoice;
import uz.pdp.task1.mapper.BaseMapper;
import uz.pdp.task1.payload.InvoiceCreateDto;
import uz.pdp.task1.payload.InvoiceDto;
import uz.pdp.task1.payload.InvoiceUpdateDto;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,componentModel = "spring")
@Component
public interface InvoiceMapper extends BaseMapper<Invoice,InvoiceDto, InvoiceCreateDto, InvoiceUpdateDto> {

    @Mapping(target = "orderId",source = "entity.order.id")
    @Override
    InvoiceDto toDto(Invoice entity);
}
