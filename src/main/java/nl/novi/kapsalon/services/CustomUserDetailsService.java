package nl.novi.kapsalon.services;

import nl.novi.kapsalon.models.User;
import nl.novi.kapsalon.repositories.UserRepository;
import nl.novi.kapsalon.utils.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepos;

    public CustomUserDetailsService(UserRepository userRepos) {
        this.userRepos = userRepos;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Hier zorgen dat er een userDetails object uitkomt. Dat doe je door in de repos te zoeken op username
        Optional<User> ou = userRepos.findUserByUsername(username);
        if (ou.isPresent()) {
            User user = ou.get();
            return new CustomUserDetails(user);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
