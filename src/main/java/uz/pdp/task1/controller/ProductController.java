package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.payload.ProductCreateDto;
import uz.pdp.task1.payload.ProductDto;
import uz.pdp.task1.payload.ProductUpdateDto;
import uz.pdp.task1.payload.response.HighDemandProductDto;
import uz.pdp.task1.payload.response.ProductInBulkResponse;
import uz.pdp.task1.payload.response.Result;

import uz.pdp.task1.payload.temp.GenericDto;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.ser.impl.ProductServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductServiceImpl productService;


    @PostMapping("/add-product")
    public ResponseEntity<?> add(@RequestBody ProductCreateDto productDto) {
        ResponseEntity<DataDto<GenericDto>> dataDtoResponseEntity = productService.create(productDto);
        return dataDtoResponseEntity;
    }


    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        ResponseEntity<DataDto<List<ProductDto>>> all = productService.getAll();
        return all;
    }


    @GetMapping("/get-one/{id}")
    public ResponseEntity<?> getOneById(@PathVariable Long id) {
            ResponseEntity<DataDto<ProductDto>> dataDtoResponseEntity = productService.get(id);
        return dataDtoResponseEntity;

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ResponseEntity<DataDto<Boolean>> delete = productService.delete(id);
        return delete;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> edit(@RequestBody ProductUpdateDto productDto) {
        ResponseEntity<DataDto<ProductDto>> update = productService.update(productDto);
        return update;
    }


    @GetMapping("/high_demand_products")
    public ResponseEntity<?> getHighDemandProducts() {
        ResponseEntity<DataDto<List<HighDemandProductDto>>> highDemandProducts = productService.getHighDemandProducts();
        return highDemandProducts;

    }


    @GetMapping("/bulk_products")
    public ResponseEntity<?> getBulkProducts() {
        ResponseEntity<DataDto<List<ProductInBulkResponse>>> productsInBulk = productService.getProductsInBulk();
        return productsInBulk;

    }

}
