package nl.novi.kapsalon.dtos;

import java.util.List;

public class BillDto {
    public Long id;
    public Long customerId;
    public Long hairdresserId;
    public List<Long> treatments;
    public List<Long> products;
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

    public List<Long> getTreatments() {
        return treatments;
    }

    public List<Long> getProducts() {
        return products;
    }

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

    public void setTreatments(List<Long> treatments) {
        this.treatments = treatments;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
}
