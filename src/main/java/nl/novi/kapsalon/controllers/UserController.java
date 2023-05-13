package nl.novi.kapsalon.controllers;

import nl.novi.kapsalon.models.User;
import nl.novi.kapsalon.repositories.UserRepository;
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
    private List<User> users = new ArrayList<>();

    @Autowired
    UserRepository userrepos;

//    @PostMapping("")
//    @ResponseBody
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        users.add(user);
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }

    @PostMapping("")
    public ResponseEntity<User> createuser(@RequestBody User user) {
        userrepos.save(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + user.getId()).toUriString());
        return ResponseEntity.created(uri).body(user);
    }

//    @GetMapping("")
//    public ResponseEntity<List<User>> getUsers() {
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @GetMapping("")
    public ResponseEntity<Iterable<User>> getUsers() {
        return ResponseEntity.ok(userrepos.findAll());
    }

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

    @GetMapping("search")
    public ResponseEntity<Iterable<User>> getUserBasedOnSubString(@RequestParam String substring) {
        return ResponseEntity.ok(userrepos.findUsersByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(substring, substring));
    }


    // Na toevoegen repository werkt deze niet meer
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody User user) {
        if (id >= 0 && id < users.size()) {
            users.set(id, user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Deze persoon staat niet in ons systeem.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        if (id >= 0 && id < users.size()) {
            users.remove(id);
            return new ResponseEntity<>("Deze persoon is succesvol verwijderd uit het systeem.", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
