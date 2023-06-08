package nl.novi.kapsalon.configs;

import nl.novi.kapsalon.filters.JwtRequestFilter;
import nl.novi.kapsalon.repositories.UserRepository;
import nl.novi.kapsalon.services.CustomUserDetailsService;
import nl.novi.kapsalon.services.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(JwtService service, UserRepository userRepos) {
        this.jwtService = service;
        this.userRepository = userRepos;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder, UserDetailsService udService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(udService)
                .passwordEncoder(encoder)
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(this.userRepository) {
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/agenda").hasAuthority("Customer")
                .requestMatchers(HttpMethod.POST, "/agenda").hasAnyAuthority("Hairdresser", "Owner")
                .requestMatchers(HttpMethod.PUT, "/agenda/**").hasAnyAuthority("Hairdresser", "Owner")
                .requestMatchers(HttpMethod.DELETE, "/agenda/**").hasAnyAuthority("Hairdresser", "Owner")
                .requestMatchers("/bill/**").hasAnyAuthority("Hairdresser", "Owner")
                .requestMatchers("/products/**").hasAnyAuthority("Hairdresser", "Owner")
                .requestMatchers("/treatments/**").hasAnyAuthority("Hairdresser", "Owner")
                .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                .requestMatchers("/**").permitAll()
                .anyRequest().denyAll()
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
