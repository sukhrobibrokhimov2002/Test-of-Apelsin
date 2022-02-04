package uz.pdp.task1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.pdp.task1.entity.temp.AbstractTemplate;
import uz.pdp.task1.payload.temp.GenericDto;

@Mapper(componentModel = "spring")
@Component
public interface GenericMapper {
    GenericDto fromDomain(AbstractTemplate domain);
}
