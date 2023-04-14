package com.jtb.taxpayerws.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleResourceAlreadyExists(AuthenticationException ex) {
        ErrorDetails details = new ErrorDetails();
        details.setStatus(HttpStatus.FORBIDDEN.value());
        details.setMessage("FORBIDDEN");
        details.setInfo(ex.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).contentType(MediaType.APPLICATION_JSON).body(details);
    }

    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        ErrorDetails details = new ErrorDetails();
        details.setStatus(ex.getStatus().value());
        details.setMessage(ex.getStatus().toString());
        details.setInfo(ex.getReason());

        return ResponseEntity.status(ex.getStatus()).contentType(MediaType.APPLICATION_JSON).body(details);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaughtExceptions(Exception ex) {
        ErrorDetails details = new ErrorDetails();
        details.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        details.setMessage("INTERNAL_SERVER_ERROR");
        details.setInfo(ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(details);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails details = new ErrorDetails();
        details.setStatus(status.value());
        details.setMessage(status.toString());
        details.setInfo(status.getReasonPhrase());

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            details.addErrors(fieldName, errorMessage);
        });

        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(details);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails details = new ErrorDetails();
        details.setStatus(status.value());
        details.setMessage(status.toString());
        details.setInfo("could not parse JSON");

        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(details);
    }

    @Override   // catch any other exception for standard error message handling
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails details = new ErrorDetails();
        details.setStatus(status.value());
        details.setMessage(ex.getMessage());

        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(details);
    }
}
