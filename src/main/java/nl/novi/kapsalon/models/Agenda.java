package nl.novi.kapsalon.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="agenda")
public class Agenda extends BaseModel {

    private Long customerId;
    private Long hairdresserId;
    private LocalDate date;
    private Integer timeSlot;
    private Boolean timeSlotAvailable;
}
