package nl.novi.kapsalon.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
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

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public String getResidence() {
        return residence;
    }

    public String getInCaseOfEmergencyContact() {
        return inCaseOfEmergencyContact;
    }

    public String getEmergencyContactPhoneNumber() {
        return emergencyContactPhoneNumber;
    }

    public String getPreferredHairdresser() {
        return preferredHairdresser;
    }

    public String getNotes() {
        return notes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public void setInCaseOfEmergencyContact(String inCaseOfEmergencyContact) {
        this.inCaseOfEmergencyContact = inCaseOfEmergencyContact;
    }

    public void setEmergencyContactPhoneNumber(String emergencyContactPhoneNumber) {
        this.emergencyContactPhoneNumber = emergencyContactPhoneNumber;
    }

    public void setPreferredHairdresser(String preferredHairdresser) {
        this.preferredHairdresser = preferredHairdresser;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
