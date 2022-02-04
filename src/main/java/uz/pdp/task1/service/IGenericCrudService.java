package uz.pdp.task1.service;

import uz.pdp.task1.entity.temp.AbstractTemplate;
import uz.pdp.task1.payload.temp.GenericDto;

import java.io.Serializable;

public interface IGenericCrudService <T extends AbstractTemplate,
        D extends GenericDto,CR,UP,ID extends Serializable>{

}
