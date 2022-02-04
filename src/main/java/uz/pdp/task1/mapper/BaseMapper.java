package uz.pdp.task1.mapper;

import org.mapstruct.MappingTarget;
import uz.pdp.task1.entity.temp.AbstractTemplate;

import java.util.List;
/**
 * @param <E>  - Entity
 * @param <D>  - Dto
 * @param <CD> - CreateDTO
 * @param <UD> - UpdateDTO
 */
public interface BaseMapper<E extends AbstractTemplate,D ,CD,UD> {
    D toDto(E entity);

    E fromDto(D dto);

    List<D> toDto(List<E> entityList);

    List<E> fromDto(List<D> dtoList);

    E fromCreateDto(CD createDto);

    E fromUpdateDto(UD updateDto, @MappingTarget E entity);

}
