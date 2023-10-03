package com.klasha.country.exceptions;

import com.klasha.country.dtos.ResponseDto;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.HashMap;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<HashMap<String, Object>> genericException(GenericException e) {
        log.error("Exception was thrown:: ", e);
        return ResponseEntity.status(e.getHttpStatus())
                .body(mapErrors(e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<HashMap<String, Object>> handleConstraintViolationException(Exception e) {
        log.error("Exception was thrown:: ", e);
        return ResponseEntity.badRequest()
                .body(mapErrors(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HashMap<String, Object>> handleInternalServerError(Exception e) {
        log.error("Exception was thrown:: ", e);
        return ResponseEntity.internalServerError()
                .body(mapErrors("An error occurred. Try again later"));
    }

    private HashMap<String, Object> mapErrors(String... messages) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("status", ResponseDto.Status.ERROR);
        response.put("errors", messages);
        return response;
    }
}
