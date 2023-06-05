package nl.novi.kapsalon.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import nl.novi.kapsalon.models.Role;

public class UserDto {
    public Long id;

    @NotBlank
    public String firstName;

    @NotBlank
    public String lastName;

    @Email
    public String email;

    @Size(min = 6)
    public String password;

    public String address;
    public Integer houseNumber;
    public String residence;
    public String inCaseOfEmergencyContact;
    public String emergencyContactPhoneNumber;
    public String preferredHairdresser;
    public String notes;
    public Role role;
}
