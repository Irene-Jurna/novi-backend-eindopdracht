package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.ProductDto;
import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
import nl.novi.kapsalon.models.Product;
import nl.novi.kapsalon.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepos;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepos, ModelMapper modelMapper) {
        this.productRepos = productRepos;
        this.modelMapper = modelMapper;
    }

    public Long createProduct(ProductDto pDto) {
        Product product = transferDtoToProduct(pDto);
        productRepos.save(product);
        return product.getId();
    }

    public List<ProductDto> getAllProducts() {
        Iterable<Product> products = productRepos.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product prod : products) {
            productDtoList.add(transferProductToDto(prod));
        }
        return productDtoList;
    }

    public void updateProduct(Long id, ProductDto productForUpdate) {
        Optional<Product> optionalProduct = productRepos.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ResourceNotFoundException("Het is kwaad kammen daar geen haar is: dit product-id staat niet in het systeem");
        } else {
            Product existingProduct = optionalProduct.get();
            Product productToBeSaved = transferDtoToProduct(productForUpdate);
            productToBeSaved.setId(existingProduct.getId());
            productRepos.save(productToBeSaved);
        }
    }

    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepos.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ResourceNotFoundException("Daar zit een haar in de boter: dit product-id staat niet in het systeem");
        } else {
            productRepos.deleteById(id);
        }
    }

    public Product transferDtoToProduct(ProductDto pDto) {
        return modelMapper.map(pDto, Product.class);
    }

    public ProductDto transferProductToDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

}
