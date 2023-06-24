package nl.novi.kapsalon.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "agendas")
public class Agenda extends BaseModel {

//    private LocalDate date;

    @ElementCollection
    private List<String> workingDays;

    @ElementCollection
    private List<Integer> timeSlotsPerDay;

    @ManyToOne
    @JoinColumn(name = "hairdresser_id")
    private User hairdresser;

}