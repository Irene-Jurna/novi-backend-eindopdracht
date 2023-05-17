package nl.novi.kapsalon.controllers;

import jakarta.validation.Valid;
import nl.novi.kapsalon.dtos.UserDto;
import nl.novi.kapsalon.models.User;
import nl.novi.kapsalon.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userservice;
    private List<User> users = new ArrayList<>();

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @PostMapping("")
    public ResponseEntity<Object> createuser(@Valid @RequestBody UserDto userDto, BindingResult br) {

        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage() + "\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Long id = userservice.createUser(userDto);
        userDto.id = id;
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + id).toUriString());
        return ResponseEntity.created(uri).body(userDto);
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> dtoList = userservice.getAllUsers();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        UserDto userDto = userservice.getUser(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("search")
    public ResponseEntity<List<UserDto>> getUserBasedOnSubString (@RequestParam String subString) {
        List<UserDto> usersWithSubstring = userservice.getUsersBasedOnSubString(subString, subString);
        return ResponseEntity.ok(usersWithSubstring);
    }

    // Na toevoegen repository werkt deze niet meer
//    @PutMapping("/{id}")
//    public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody User user) {
//        if (id >= 0 && id < users.size()) {
//            users.set(id, user);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Deze persoon staat niet in ons systeem.", HttpStatus.NOT_FOUND);
//        }
//    }

    // Nu wordt er user die je wil updaten als nieuwe user erin gezet (later op te lossen met de server: update methode)
//    @PutMapping("/{id}")
//    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody User user) {
//        if (userrepos.findById(id).isPresent()) {
//            userrepos.save(user);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        if (id >= 0 && id < users.size()) {
            users.remove(id);
            return new ResponseEntity<>("Deze persoon is succesvol verwijderd uit het systeem.", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
