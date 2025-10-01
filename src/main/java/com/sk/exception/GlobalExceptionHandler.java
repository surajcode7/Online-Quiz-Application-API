package com.sk.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sk.utility.Constants;

@ControllerAdvice
public class GlobalExceptionHandler {

	//handling ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleResourceNotFound(ResourceNotFoundException ex){
		
		ErrorMessage error = new ErrorMessage(HttpStatus.NOT_FOUND.value(),
				                               Constants.FAILURE,
				                               ex.getMessage(),
				                               LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	//handling bad request
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorMessage> handleBadRequest(BadRequestException ex){
		
		ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
				                              ex.getMessage(),
				                              LocalDateTime.now());
		
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.BAD_REQUEST);
	}
	
	 // Handle validation errors (from @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                validationErrors.put(error.getField(), error.getDefaultMessage()));

      ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), Constants.FAILED, validationErrors, LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
      // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex) {
        ErrorMessage error = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred: " + ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
     
}
