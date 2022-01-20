package com.demo.nisum.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.nisum.common.ErrorMessage;

@RestControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage ValidationExceptionHandler(MethodArgumentNotValidException exception) {
		ErrorMessage errorMessage = new ErrorMessage();
		ObjectError oError = exception.getAllErrors().get(0);
		
		errorMessage.setMensaje(String.format("campo %s  %s", exception.getFieldError().getField(), oError.getDefaultMessage()));
		return errorMessage;
	}
}
