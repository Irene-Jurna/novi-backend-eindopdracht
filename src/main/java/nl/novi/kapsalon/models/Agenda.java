package nl.novi.kapsalon.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="agendas")
public class Agenda extends BaseModel {

    private Long hairdresserId;
    private List<Integer> timeSlots;
}
