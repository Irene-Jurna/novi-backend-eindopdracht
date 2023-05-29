package nl.novi.kapsalon.dtos;

import java.time.LocalDate;

public class AgendaDto {
    public Long customerId;
    public Long hairdresserId;
    public LocalDate date;
    public Integer timeSlot;
    public Boolean timeSlotAvailable;

    public Long getCustomerId() {
        return customerId;
    }

    public Long getHairdresserId() {
        return hairdresserId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getTimeSlot() {
        return timeSlot;
    }

    public Boolean getTimeSlotAvailable() {
        return timeSlotAvailable;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setHairdresserId(Long hairdresserId) {
        this.hairdresserId = hairdresserId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTimeSlot(Integer timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setTimeSlotAvailable(Boolean timeSlotAvailable) {
        this.timeSlotAvailable = timeSlotAvailable;
    }
}
