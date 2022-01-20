package com.demo.nisum.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

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
	
	@ExceptionHandler(BusinessLogicException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage validBusinessLogicException(BusinessLogicException nisumException) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMensaje(nisumException.getMessage());
		return errorMessage;
	}
	
	@ExceptionHandler(HttpServerErrorException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage ValidNisumException(HttpServerErrorException serverError) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMensaje(serverError.getMessage());
		serverError.printStackTrace();
		return errorMessage;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage ValidGenericException(Exception serverError) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMensaje(serverError.getMessage());
		serverError.printStackTrace();
		return errorMessage;
	}
}
