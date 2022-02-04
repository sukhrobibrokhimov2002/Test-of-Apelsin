package uz.pdp.task1.mapper.map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.pdp.task1.entity.Product;
import uz.pdp.task1.mapper.BaseMapper;
import uz.pdp.task1.payload.ProductCreateDto;
import uz.pdp.task1.payload.ProductDto;
import uz.pdp.task1.payload.ProductUpdateDto;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,componentModel = "spring")
@Component
public interface ProductMapper
        extends BaseMapper<Product, ProductDto, ProductCreateDto, ProductUpdateDto> {
    @Mapping(target = "categoryId",source = "entity.category.id")
    @Override
    ProductDto toDto(Product entity);
}
