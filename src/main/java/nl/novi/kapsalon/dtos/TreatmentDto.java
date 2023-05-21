package nl.novi.kapsalon.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreatmentDto {
    public Long id;

    public String name;

    public Integer durationInMinutes;

    public Double price;
}
