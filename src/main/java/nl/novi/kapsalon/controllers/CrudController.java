package nl.novi.kapsalon.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import nl.novi.kapsalon.services.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * The crud controller
 *
 * @param <REQUEST>  The request
 * @param <RESPONSE> The response
 */
@RequiredArgsConstructor
public class CrudController<REQUEST, RESPONSE> implements BaseController {

    private static final String ID_PARAM = "id";

    /**
     * The target service bean
     */
    private final CrudService<REQUEST, RESPONSE, ?> crudService;

    /**
     * The constructor for wiring the target service bean
     */
    public CrudService<REQUEST, RESPONSE, ?> getCrudService() {
        return crudService;
    }

    /**
     * API for adding new record
     *
     * @param request The request
     * @return {@link ResponseEntity<RESPONSE>} the response
     */
    @PostMapping
    public ResponseEntity<RESPONSE> add(@Valid REQUEST request) {
        RESPONSE add = getCrudService().create(request);
        return responseBuilder(HttpStatus.CREATED, add);
    }

    /**
     * API for editing a record
     *
     * @param request The request
     * @return {@link ResponseEntity<RESPONSE>} the response
     */
    @PutMapping
    public ResponseEntity<RESPONSE> edit(@NotBlank(message = "id must be determined") @RequestParam(ID_PARAM) Long id, @Valid REQUEST request) {
        RESPONSE update = getCrudService().update(id, request);
        return okResponse(update);
    }

    /**
     * API for a record deletion
     *
     * @param id The record ID
     * @return {@link ResponseEntity<RESPONSE>} the response
     */
    @DeleteMapping
    public void delete(@RequestParam(ID_PARAM)
                       @NotNull(message = "id must be determined.") Long id) {
        getCrudService().delete(id);
    }

    /**
     * API for getting a single record of getting a list of records
     *
     * @param id The record ID
     * @return {@link ResponseEntity<RESPONSE>} the response
     */
    @GetMapping("/{id}")
    public ResponseEntity<RESPONSE> get(@NotBlank(message = "id must be determined.") @RequestParam(ID_PARAM) Long id) {
        return okResponse(
                getCrudService().get(id)
        );
    }

    @GetMapping("")
    public ResponseEntity<RESPONSE> getAll() {
        return okResponse(crudService.getAll());
    }

}