package nl.novi.kapsalon.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="treatments")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Index(name = "name_index", columnList = "name") // Database query optimization based on adding the index
public class Treatment extends BaseEntity{

    private String name;
    private Integer durationInMinutes;
    private Double price;

}
