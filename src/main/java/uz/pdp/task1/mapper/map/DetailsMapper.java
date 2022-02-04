package uz.pdp.task1.mapper.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.pdp.task1.entity.Detail;
import uz.pdp.task1.mapper.BaseMapper;
import uz.pdp.task1.payload.DetailsCreateDto;
import uz.pdp.task1.payload.DetailsDto;
import uz.pdp.task1.payload.DetailsUpdateDto;


@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,componentModel = "spring")
@Component
public interface DetailsMapper extends BaseMapper<Detail, DetailsDto, DetailsCreateDto, DetailsUpdateDto> {

    @Mapping(target = "orderId",source="entity.order.id")
    @Mapping(target = "productId",source="entity.product.id")
    @Override
    DetailsDto toDto(Detail entity);
}
