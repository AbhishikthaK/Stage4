package com.cognizant;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResponseEntity<Object> response = null;
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		List<String> errorMessages = errors.stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList());
		response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
		return response;
	}

}
