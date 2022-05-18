package com.accenture.gradetool.core.error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ExceptionLogger exceptionLogger;

    @Autowired
    public CustomGlobalExceptionHandler(ExceptionLogger exceptionLogger) {
        this.exceptionLogger = exceptionLogger;
    }

    /**
     * This method handles all RuntimeExceptions which are not thrown intentionally and / or caught in this exception handler. It returns a
     * response entity with status code 500 and the ID of the logged exception which occurred.
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Map<String, Object>> generalException(RuntimeException e) {
        String exceptionId = exceptionLogger.logException(e);

        Map<String, Object> map = new HashMap<>();
        map.put("id", exceptionId);
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
        HttpStatus status,
        WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();

        // Put all fields which have violated constraints
        List<ViolatedField> constraintViolations = new ArrayList<>();

        ex.getBindingResult().getFieldErrors()
            .forEach((fieldError) -> constraintViolations.add(new ViolatedField(fieldError.getField(), fieldError.getRejectedValue())));

        body.put("violatedFields", constraintViolations);
        body.put("exception", ex);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> accessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(new ExceptionResponse(e), HttpStatus.FORBIDDEN);
    }

    /**
     * This method handles NoSuchElementExceptions and maps them to a response with status code 404
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> noSuchElementException(NoSuchElementException e) {
        return getResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    /**
     * This method handles BadRequestExceptions and maps them to a response with status 400
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, String>> handleBadRequestException(BadRequestException e) {
        return getResponseEntity(e, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method handles NotUniqueExceptions and maps them to a response with status 409
     */
    @ExceptionHandler(NotUniqueException.class)
    public ResponseEntity<Map<String, String>> handleNotUniqueException(NotUniqueException e) {
        return getResponseEntity(e, HttpStatus.CONFLICT);
    }

    /**
     * This method handles UnsupportedOperationExceptions and maps them to a response with status 405
     */
    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<Map<String, String>> handleUnsupportedOperationException(UnsupportedOperationException e) {
        return getResponseEntity(e, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Map<String, String>> handleInvalidEmailException(InvalidEmailException e) {
        return getResponseEntity(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<Map<String, String>> handleIncorrectPasswordException(IncorrectPasswordException e) {
        return getResponseEntity(e, HttpStatus.CONFLICT);
    }

    //--------------------------------------------- EXAMPLE -----------------------------------------------------------

    /*@ExceptionHandler(MyCustomException.class)
    public ResponseEntity<Map<String, String>> handleMyCustomException(MyCustomException e) {

        * do whatever is needed *

        return getResponseEntity(e, [some http status]);
    }*/

    //--------------------------------------------- EXAMPLE -----------------------------------------------------------

    /**
     * Creates a ResponseEntity with a developer message and a status from an Exception
     *
     * @param e      the Exception to get the message from
     * @param status the ResponseEntity's status
     * @return a ResponseEntity with the Exception's message as a "developerMessage" attribute in it's body as well as the given response status
     */
    private ResponseEntity<Map<String, String>> getResponseEntity(Exception e, HttpStatus status) {
        Map<String, String> map = new HashMap<>();
        map.put("developerMessage", e.getMessage());

        return new ResponseEntity<>(map, status);
    }

}