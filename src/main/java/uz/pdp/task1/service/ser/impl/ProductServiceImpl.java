package uz.pdp.task1.service.ser.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Category;
import uz.pdp.task1.entity.Product;
import uz.pdp.task1.mapper.GenericMapper;
import uz.pdp.task1.mapper.map.ProductMapper;
import uz.pdp.task1.payload.ProductCreateDto;
import uz.pdp.task1.payload.ProductDto;
import uz.pdp.task1.payload.ProductUpdateDto;
import uz.pdp.task1.payload.response.HighDemandProductDto;
import uz.pdp.task1.payload.response.ProductInBulkResponse;
import uz.pdp.task1.payload.temp.GenericDto;
import uz.pdp.task1.repository.CategoryRepository;
import uz.pdp.task1.repository.ProductRepository;
import uz.pdp.task1.response.AppErrorDto;
import uz.pdp.task1.response.DataDto;
import uz.pdp.task1.service.GenericCrudService;
import uz.pdp.task1.service.ser.IProductService;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl extends GenericCrudService
        <Product, ProductDto, ProductCreateDto, ProductUpdateDto> implements IProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    GenericMapper genericMapper;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull ProductCreateDto createDto) {
        Product product = productMapper.fromCreateDto(createDto);
        Optional<Category> categoryOptional = categoryRepository.findById(createDto.getCategoryId());
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "Category not found")), HttpStatus.NOT_FOUND);
        }
        product.setCategory(categoryOptional.get());
        productRepository.save(product);
        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(product)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<ProductDto>>> getAll() {
        List<Product> all = productRepository.findAll();
        List<ProductDto> productDtos = productMapper.toDto(all);
        return new ResponseEntity<>(new DataDto<>(productDtos), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<ProductDto>> get(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "PRODUCT not found")), HttpStatus.NOT_FOUND);

        Product product = productOptional.get();
        ProductDto productDto = productMapper.toDto(product);
        return new ResponseEntity<>(new DataDto<>(productDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<ProductDto>> update(ProductUpdateDto dto) {
        Optional<Product> optionalProduct = productRepository.findById(dto.getId());
        Optional<Category> optionalCategory = categoryRepository.findById(dto.getCategoryId());
        if (!optionalProduct.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "PRODUCT not found")), HttpStatus.NOT_FOUND);
        if (!optionalCategory.isPresent())
            return new ResponseEntity<>(new DataDto<>(new AppErrorDto(404, "CATEGORY not found")), HttpStatus.NOT_FOUND);


        Product product = optionalProduct.get();
        Product finalProduct = productMapper.fromUpdateDto(dto, product);
        finalProduct.setCategory(optionalCategory.get());
        productRepository.save(finalProduct);
        return get(finalProduct.getId());
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDto<>(false), HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<DataDto<List<HighDemandProductDto>>> getHighDemandProducts() {
        List<HighDemandProductDto> highDemandProducts = productRepository.getHighDemandProducts();
        return new ResponseEntity<>(new DataDto<>(highDemandProducts), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<ProductInBulkResponse>>> getProductsInBulk() {
        List<ProductInBulkResponse> productsInBulk = productRepository.getProductsInBulk();
        return new ResponseEntity<>(new DataDto<>(productsInBulk), HttpStatus.OK);
    }
}
