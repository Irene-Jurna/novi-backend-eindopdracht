package nl.novi.kapsalon.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductDto {
    public Long id;

    @NotBlank
    public String name;

    @Digits(integer = 3, fraction = 2)
    public Double purchasePrice;

    @Digits(integer = 3, fraction = 2)
    public Double price;

    @Positive
    public Integer inStock;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }
}
