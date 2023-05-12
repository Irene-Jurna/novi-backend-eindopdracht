package nl.novi.kapsalon.controllers;

import nl.novi.kapsalon.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private List<User> users = new ArrayList<>();

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user) {
        users.add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(users, HttpStatus.OK);
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

    @GetMapping("search")
    public ResponseEntity<List<User>> getUserBasedOnSubString(@RequestParam String substring) {
        List<User> usersWithSubString = new ArrayList<>();
        for (User user : users) {
            if (user.getFirstName().contains(substring) || user.getLastName().contains(substring)) {
                usersWithSubString.add(user);
            }
        }
        return new ResponseEntity<>(usersWithSubString, HttpStatus.OK);
    }

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
