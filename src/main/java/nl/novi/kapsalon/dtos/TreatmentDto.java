package nl.novi.kapsalon.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class TreatmentDto {
    public Integer id;

    @NotBlank
    public String name;

    @Min(value=0) @Max(value=180)
    public Integer durationInMinutes;

    @Digits(integer=3, fraction = 2)
    public Double price;
}
