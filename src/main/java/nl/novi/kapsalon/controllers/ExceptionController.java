package nl.novi.kapsalon.controllers;

import lombok.extern.slf4j.Slf4j;
import nl.novi.kapsalon.dtos.ServerErrorResponse;
import nl.novi.kapsalon.exceptions.BusinessException;
import nl.novi.kapsalon.exceptions.DuplicateNameException;
import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Veranderd van ControllerAdvice to RestControllerAdvice
@RestControllerAdvice
@Slf4j
// @Slf4j for log management
public class ExceptionController implements BaseController {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateNameException.class)
    public ResponseEntity<String> handleDuplicateNameException(DuplicateNameException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(409));
    }

    /**
     * Manage {@link Exception}
     *
     * @param exception The exception
     * @return {@link ResponseEntity<ErrorResponse>}
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception exception) {
        log.error(exception.getLocalizedMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(makeExceptionResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /**
     * Manage {@link ServletRequestBindingException}
     *
     * @param exception The exception
     * @return {@link ResponseEntity<ErrorResponse>}
     */
    @ExceptionHandler(value = ServletRequestBindingException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(ServletRequestBindingException exception) {
        log.error(exception.getLocalizedMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(makeExceptionResponse(exception, HttpStatus.BAD_REQUEST));
    }

    /**
     * Manage {@link BusinessException}
     *
     * @param businessException The exception
     * @return {@link ResponseEntity<ErrorResponse>}
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorResponse> businessExceptionHandler(BusinessException businessException) {
        log.error(businessException.getLocalizedMessage());
        return responseBuilder( businessException.getHttpStatus(),
                makeExceptionResponse(businessException, businessException.getHttpStatus())
        );
    }

    private ErrorResponse makeExceptionResponse(Throwable throwable, HttpStatus status) {
        return new ServerErrorResponse(status, throwable.getLocalizedMessage());
    }


}
