package nl.novi.kapsalon.controllers;

import nl.novi.kapsalon.dtos.UserDto;
import nl.novi.kapsalon.models.User;
import nl.novi.kapsalon.repositories.UserRepository;
import nl.novi.kapsalon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//    @PostMapping("")
//    @ResponseBody
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        users.add(user);
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }

    @PostMapping("")
    public ResponseEntity<UserDto> createuser(@RequestBody UserDto userDto) {
        Long id = userservice.createUser(userDto);
        userDto.id = id;
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + id).toUriString());
        return ResponseEntity.created(uri).body(userDto);
    }

//    @GetMapping("")
//    public ResponseEntity<List<User>> getUsers() {
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

//    @GetMapping("")
//    public ResponseEntity<Iterable<User>> getUsers() {
//        return ResponseEntity.ok(userrepos.findAll());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        if (id >= 0 && id < users.size()) {
            User user = users.get(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Na toevoegen repository werkt deze niet meer
//    @GetMapping("search")
//    public ResponseEntity<List<User>> getUserBasedOnSubString(@RequestParam String substring) {
//        List<User> usersWithSubString = new ArrayList<>();
//        for (User user : users) {
//            if (user.getFirstName().toLowerCase().contains(substring.toLowerCase()) || user.getLastName().toLowerCase().contains(substring.toLowerCase())) {
//                usersWithSubString.add(user);
//            }
//        }
//        return new ResponseEntity<>(usersWithSubString, HttpStatus.OK);
//    }

//    @GetMapping("search")
//    public ResponseEntity<Iterable<User>> getUserBasedOnSubString(@RequestParam String substring) {
//        return ResponseEntity.ok(userrepos.findUsersByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(substring, substring));
//    }


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
