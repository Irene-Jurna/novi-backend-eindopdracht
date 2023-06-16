package nl.novi.kapsalon.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationDto {
    private Long agendaId;
    private LocalDateTime reservationDateTime;
    private int startTime;
    private int endTime;

    public Long getAgendaId() {
        return agendaId;
    }

    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setAgendaId(Long agendaId) {
        this.agendaId = agendaId;
    }

    public void setReservationDateTime(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
