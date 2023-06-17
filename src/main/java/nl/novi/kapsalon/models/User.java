package nl.novi.kapsalon.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User extends BaseModel {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String address;
    private Integer houseNumber;
    private String residence;
    private String inCaseOfEmergencyContact;
    private String emergencyContactPhoneNumber;
    private String preferredHairdresser;
    private String notes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleName")
    private Role role;

    public Role getRole() {
        return role;
    }
}
