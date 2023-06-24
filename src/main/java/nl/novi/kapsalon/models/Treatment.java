package nl.novi.kapsalon.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "treatments")
public class Treatment extends BaseModel {

    private String name;
    private Integer durationInMinutes;
    private Double price;

    @ManyToMany(mappedBy = "treatments")
    List<Bill> bills;

    public Treatment(String name, Integer durationInMinutes, Double price) {
    }
}
