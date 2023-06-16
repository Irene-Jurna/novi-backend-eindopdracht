package nl.novi.kapsalon.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BillDto {
    public Long id;

    @NotNull
    public Long customerId;

    @NotNull
    public Long hairdresserId;

    public List<Long> treatmentIds = new ArrayList<>();
    public List<Long> productIds = new ArrayList<>();
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

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
