package nl.novi.kapsalon.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    @ManyToOne
    @JoinColumn(name = "hairdresser_id")
    private User hairdresser;

    private LocalDateTime reservationDateTime;
    private Integer timeSlot;
}
