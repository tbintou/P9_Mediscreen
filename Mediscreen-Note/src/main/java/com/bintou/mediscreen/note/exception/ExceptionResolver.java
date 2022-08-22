package com.bintou.mediscreen.note.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionResolver {

    /*
    Controller exception bad request
     */
    @ExceptionHandler
    public ResponseEntity handlerValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ExceptionResponse handlerResourceNotFoundException (ResourceNotFoundException resourceNotFoundException, HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(404);
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 404, "Notes introuvables avec ID",
                request.getRequestURI());
        log.info("Erreur : " + resourceNotFoundException + " " + response);
        return exceptionResponse;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ExceptionResponse handlerIllegalArgumentException(IllegalArgumentException illegalArgumentException, HttpServletRequest request, HttpServletResponse responseCode) {
        responseCode.setStatus(400);
        ExceptionResponse response = new ExceptionResponse(new Date(), 400,
                illegalArgumentException.getLocalizedMessage(),
                request.getRequestURI());
        log.info("Erreur : " + illegalArgumentException + " " + response);
        return response;
    }
}
