package nl.novi.kapsalon.dtos;

import java.util.List;

public class AgendaDto {
    public Long id;
    public Long hairdresserId;
    public List<Integer> timeSlots;
    public Boolean timeSlotAvailable;

    public Long getId() {
        return id;
    }

    public Long getHairdresserId() {
        return hairdresserId;
    }

    public List<Integer> getTimeSlots() {
        return timeSlots;
    }

    public Boolean getTimeSlotAvailable() {
        return timeSlotAvailable;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHairdresserId(Long hairdresserId) {
        this.hairdresserId = hairdresserId;
    }

    public void setTimeSlots(List<Integer> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public void setTimeSlotAvailable(Boolean timeSlotAvailable) {
        this.timeSlotAvailable = timeSlotAvailable;
    }
}
