package nl.novi.kapsalon.controllers;

import jakarta.validation.Valid;
import nl.novi.kapsalon.dtos.ProductDto;
import nl.novi.kapsalon.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @PostMapping("")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductDto pDto) {
        productService.createProduct(pDto);
        return new ResponseEntity<>(pDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_HAIRDRESSER', 'ROLE_OWNER')")
    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> dtoList = productService.getAllProducts();
        return ResponseEntity.ok(dtoList);
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        productService.updateProduct(id, productDto);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
