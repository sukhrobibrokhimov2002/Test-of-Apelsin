package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.payload.DetailsDto;
import uz.pdp.task1.payload.DetailsUpdateDto;
import uz.pdp.task1.payload.response.Result;
import uz.pdp.task1.response.DataDto;

import uz.pdp.task1.service.ser.impl.DetailsServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/details")
public class DetailsController {

    @Autowired
    DetailsServiceImpl detailsService;


    @GetMapping("get-all")
    public ResponseEntity<?> getAll() {
        ResponseEntity<DataDto<List<DetailsDto>>> all =
                detailsService.getAll();
        return all;
    }


    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> getOneById(@PathVariable Long id) {
        ResponseEntity<DataDto<DetailsDto>> dataDtoResponseEntity =
                detailsService.get(id);
        return dataDtoResponseEntity;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ResponseEntity<DataDto<Boolean>> delete = detailsService.delete(id);
        return delete;
    }


}
