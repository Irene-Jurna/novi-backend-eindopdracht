package nl.novi.kapsalon.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TreatmentRequestDto {

    @NotBlank
    public String name;

    @Min(value=0) @Max(value=180)
    public Integer durationInMinutes;

    @Digits(integer=3, fraction = 2)
    public Double price;
}
