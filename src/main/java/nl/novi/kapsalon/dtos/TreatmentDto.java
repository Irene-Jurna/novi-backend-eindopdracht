package nl.novi.kapsalon.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public class TreatmentDto {
    public Long id;

    @NotBlank
    public String name;

    @Min(value=0) @Max(value=180)
    public Integer durationInMinutes;

    @Digits(integer=3, fraction = 2)
    public Double price;

    public TreatmentDto() {
    }

    public TreatmentDto(String name, int durationInMinutes, double price) {
    }

    public TreatmentDto(Long id, String name, int durationInMinutes, double price) {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
