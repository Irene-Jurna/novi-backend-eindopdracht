package nl.novi.kapsalon.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Validated
public interface BaseController {
    default <T>ResponseEntity<T> okResponse(T body) {
        return responseBuilder(HttpStatus.OK, body);
    }

    default<T> ResponseEntity<T> responseBuilder(HttpStatus status, T body) {
        return ResponseEntity.status(status).body(body);
    }
}
