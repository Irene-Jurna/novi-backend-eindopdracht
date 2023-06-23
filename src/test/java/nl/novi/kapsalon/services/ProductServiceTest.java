package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.ProductDto;
import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
import nl.novi.kapsalon.models.Product;
import nl.novi.kapsalon.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductServiceTest {

    @Mock
    ProductRepository productRepos;

    @InjectMocks
    ProductService productService;

    @Captor
    ArgumentCaptor<Product> productArgumentCaptor;

    Product product1;
    Product product2;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        productService = new ProductService(productRepos, modelMapper);

        Long productId1 = 1L;
        Long productId2 = 2L;

        product1 = new Product("Shampoobar", 12.99, 16.00, 10, null);
        product1.setId(productId1);

        product2 = new Product("Cremespoeling", 8.99, 12.00, 30, null);
        product2.setId(productId2);
    }

    @Test
    @DisplayName("Should create correct product")
    void createProduct() {
        when(productRepos.save(product1)).thenReturn(product1);

        productService.createProduct(productService.transferProductToDto(product1));
        verify(productRepos, times(1)).save(productArgumentCaptor.capture());
        Product product = productArgumentCaptor.getValue();

        assertEquals(product1.getId(), product.getId());
        assertEquals(product1.getName(), product.getName());
        assertEquals(product1.getPurchasePrice(), product.getPurchasePrice());
        assertEquals(product1.getPrice(), product.getPrice());
        assertEquals(product1.getInStock(), product.getInStock());
    }

    @Test
    @DisplayName("Should return all products in the database")
    void getAllProducts() {
        when(productRepos.findAll()).thenReturn(List.of(product1, product2));

        List<Product> products = productRepos.findAll();
        List<ProductDto> productDtoList = productService.getAllProducts();

        assertEquals(2, productDtoList.size());

        assertEquals(products.get(0).getName(), productDtoList.get(0).getName());
        assertEquals(products.get(0).getPurchasePrice(), productDtoList.get(0).getPurchasePrice());
        assertEquals(products.get(0).getPrice(), productDtoList.get(0).getPrice());
        assertEquals(products.get(0).getInStock(), productDtoList.get(0).getInStock());
        assertEquals(products.get(0).getId(), productDtoList.get(0).getId());

        assertEquals(products.get(1).getName(), productDtoList.get(1).getName());
        assertEquals(products.get(1).getPurchasePrice(), productDtoList.get(1).getPurchasePrice());
        assertEquals(products.get(1).getPrice(), productDtoList.get(1).getPrice());
        assertEquals(products.get(1).getInStock(), productDtoList.get(1).getInStock());
        assertEquals(products.get(1).getId(), productDtoList.get(1).getId());
    }

    @Test
    @DisplayName("Should update correct product")
    void updateProduct() {
        when(productRepos.findById(1L)).thenReturn(Optional.of(product1));

        ProductDto newProductDto = new ProductDto("Shampoobar", 16.99, 20.00, 8, null);
        newProductDto.setId(product1.getId());
        when(productRepos.save(productService.transferDtoToProduct(newProductDto))).thenReturn(product1);
        productService.updateProduct(1L, newProductDto);
        verify(productRepos, times(1)).save(productArgumentCaptor.capture());
        Product captured = productArgumentCaptor.getValue();

        assertEquals(newProductDto.getId(), captured.getId());
        assertEquals(newProductDto.getName(), captured.getName());
        assertEquals(newProductDto.getPurchasePrice(), captured.getPurchasePrice());
        assertEquals(newProductDto.getPurchasePrice(), captured.getPurchasePrice());
        assertEquals(newProductDto.getPrice(), captured.getPrice());
        assertEquals(newProductDto.getInStock(), captured.getInStock());
    }

    @Test
    @DisplayName("Should not update product because the product id doesn't exists")
    void doNotUpdateProduct() throws Exception {
        ProductDto newProductDto = new ProductDto("Shampoobar", 16.99, 20.00, 8, null);
        newProductDto.setId(product1.getId());
        assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct(null, newProductDto));
    }

    @Test
    @DisplayName("Should delete correct product")
    void deleteProduct() {
        when(productRepos.findById(product1.getId())).thenReturn(Optional.of(product1));
        productService.deleteProduct(1L);
        verify(productRepos).deleteById(1L);
    }

    @Test
    @DisplayName("Should not delete product because the product id doesn't exists")
    void doNotDeleteProduct() throws Exception {
        when(productRepos.findById(product1.getId())).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            productService.deleteProduct(3L);
        });
        assertEquals("Daar zit een haar in de boter: dit product-id staat niet in het systeem", exception.getMessage());
    }
}