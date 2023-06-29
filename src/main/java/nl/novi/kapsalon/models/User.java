package nl.novi.kapsalon.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(mappedBy = "hairdresser")
    private List<Agenda> agendas;

    public User(String name, String lastName, String username, String email, String password, String address, int houseNumber, String residence, String inCaseOfEmergencyContact, String emergencyContactPhoneNumber, String preferredHairdresser, String notes, Object role) {
    }

    public Role getRole() {
        return role;
    }
}
