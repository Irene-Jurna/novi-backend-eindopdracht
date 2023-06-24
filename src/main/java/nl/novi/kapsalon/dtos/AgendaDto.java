package nl.novi.kapsalon.dtos;

import jakarta.validation.constraints.AssertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AgendaDto {
    public Long id;

//    public LocalDate date;
    public List<String> workingDays;
    public List<Integer> timeSlotsPerDay = new ArrayList<>();
    public Long hairdresserId;

    @AssertTrue(message = "De lijst met werkdagen moet even lang zijn als de lijst met tijdslots per dag")
    public boolean isListEqualLength() {
        return workingDays.size() == timeSlotsPerDay.size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public LocalDate getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }

    public List<String> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(List<String> workingDays) {
        this.workingDays = workingDays;
    }

    public List<Integer> getTimeSlotsPerDay() {
        return timeSlotsPerDay;
    }

    public void setTimeSlotsPerDay(List<Integer> timeSlotsPerDay) {
        this.timeSlotsPerDay = timeSlotsPerDay;
    }

    public Long getHairdresserId() {
        return hairdresserId;
    }

    public void setHairdresserId(Long hairdresserId) {
        this.hairdresserId = hairdresserId;
    }
}
