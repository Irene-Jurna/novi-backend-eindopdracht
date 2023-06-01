package nl.novi.kapsalon.dtos;

import nl.novi.kapsalon.models.Product;

import java.util.ArrayList;
import java.util.List;

public class BillDto {
    public Long id;
    public Long customerId;
    public Long hairdresserId;
    public List<Long> treatmentIds = new ArrayList<>();
//    public Long productId;
//    public List<Long> productIds;
    public List<Product> products = new ArrayList<>();
    public Boolean paid;

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getHairdresserId() {
        return hairdresserId;
    }

    public List<Long> getTreatmentIds() {
        return treatmentIds;
    }

//    public Long getProductId() {
//        return productId;
//    }

    //    public List<Long> getProductIds() {
//        return productIds;
//    }

    public Boolean getPaid() {
        return paid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setHairdresserId(Long hairdresserId) {
        this.hairdresserId = hairdresserId;
    }

    public void setTreatmentIds(List<Long> treatmentIds) {
        this.treatmentIds = treatmentIds;
    }

//    public void setProductIds(List<Long> productIds) {
//        this.productIds = productIds;
//    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
