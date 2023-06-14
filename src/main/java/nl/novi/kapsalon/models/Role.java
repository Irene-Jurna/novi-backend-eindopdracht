package nl.novi.kapsalon.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "roles")
public class Role {

    @Id
    private String roleName;

    @OneToMany
    private List<User> users;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
