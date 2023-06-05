package nl.novi.kapsalon.services;

import nl.novi.kapsalon.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public abstract class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepos;

    public CustomUserDetailsService(UserRepository userRepos) {
        this.userRepos = userRepos;
    }
}
