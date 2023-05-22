package nl.novi.kapsalon.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User extends BaseModel {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private Integer houseNumber;
    private String residence;
    private String inCaseOfEmergencyContact;
    private String emergencyContactPhoneNumber;
    private String preferredHairdresser;
    private String notes;

}
