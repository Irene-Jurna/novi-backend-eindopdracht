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
        try {
            Long userId = Long.parseLong(username);
            Optional<User> optionalUser = userRepos.findById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                return new CustomUserDetails(user);
            } else {
                throw new UsernameNotFoundException("User not found with id: " + userId);
            }
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Invalid user id format");
        }
    }
}
