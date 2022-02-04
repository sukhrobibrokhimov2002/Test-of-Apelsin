package uz.pdp.task1.service.ser;

import org.springframework.http.ResponseEntity;
import uz.pdp.task1.entity.Product;
import uz.pdp.task1.payload.ProductCreateDto;
import uz.pdp.task1.payload.ProductDto;
import uz.pdp.task1.payload.ProductUpdateDto;
import uz.pdp.task1.payload.response.HighDemandProductDto;
import uz.pdp.task1.payload.response.ProductInBulkResponse;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.IGenericCrudService;

import java.util.List;

public interface IProductService extends IGenericCrudService
        <Product, ProductDto, ProductCreateDto, ProductUpdateDto,Long> {

    public ResponseEntity<DataDto<List<HighDemandProductDto>>> getHighDemandProducts();
    public ResponseEntity<DataDto<List<ProductInBulkResponse>>> getProductsInBulk();
}
