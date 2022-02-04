package uz.pdp.task1.service;

import com.sun.istack.NotNull;
import org.springframework.http.ResponseEntity;
import uz.pdp.task1.entity.temp.AbstractTemplate;
import uz.pdp.task1.payload.temp.GenericDto;
import uz.pdp.task1.response.DataDto;

import java.util.List;

public abstract class GenericCrudService<T extends AbstractTemplate,
        D extends GenericDto,CR,UP> {

    public ResponseEntity<DataDto<D>> get(Long id) {
        return null;
    }

    public ResponseEntity<DataDto<List<D>>> getAll() {
        return null;
    }

    public ResponseEntity<DataDto<GenericDto>> create(@NotNull CR dto) {
        return null;
    }

    public ResponseEntity<DataDto<D>> update(@NotNull UP dto) {
        return null;
    }

    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        return null;
    }

    public void baseValidation(@NotNull T entity) {


    }
}
