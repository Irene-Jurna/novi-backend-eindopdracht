package nl.novi.kapsalon.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="treatments")
public class Treatment extends BaseModel {

    private String name;
    private Integer durationInMinutes;
    private Double price;
}
