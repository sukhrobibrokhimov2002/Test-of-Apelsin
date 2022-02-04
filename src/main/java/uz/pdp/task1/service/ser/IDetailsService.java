package uz.pdp.task1.service.ser;

import uz.pdp.task1.entity.Detail;
import uz.pdp.task1.payload.DetailsCreateDto;
import uz.pdp.task1.payload.DetailsDto;
import uz.pdp.task1.payload.DetailsUpdateDto;
import uz.pdp.task1.service.IGenericCrudService;

public interface IDetailsService extends IGenericCrudService
        <Detail, DetailsDto, DetailsCreateDto, DetailsUpdateDto,Long> {
}
