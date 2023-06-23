package nl.novi.kapsalon.dtos;

import jakarta.validation.constraints.NotNull;

public class AuthDto {

    @NotNull
    public String username;

    @NotNull
    public String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
