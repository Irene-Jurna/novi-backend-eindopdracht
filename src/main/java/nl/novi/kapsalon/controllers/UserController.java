package nl.novi.kapsalon.controllers;

import jakarta.validation.Valid;
import nl.novi.kapsalon.dtos.UserDto;
import nl.novi.kapsalon.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @PostMapping("")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDto userDto, BindingResult br) {

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
    public ResponseEntity<List<UserDto>> getUsersBasedOnSubString(@RequestParam String subString) {
        List<UserDto> usersWithSubstring = userservice.getUsersBasedOnSubString(subString, subString);
        return ResponseEntity.ok(usersWithSubstring);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("id") Long id, @RequestBody UserDto userDto) {
        userservice.updateUser(id, userDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        userservice.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
