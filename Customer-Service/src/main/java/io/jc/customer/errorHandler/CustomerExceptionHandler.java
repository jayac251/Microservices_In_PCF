package io.jc.customer.errorHandler;

import java.io.IOException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class CustomerExceptionHandler {
	@ExceptionHandler(value = { CustomerNotFoundException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse customerNotFound(Exception ex) {
		return new ErrorResponse(404, "Customer Not found, " + ex.getMessage());
	}

	@ExceptionHandler(value = { CustomerException.class,Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse customerError(Exception ex) {
		return new ErrorResponse(500, "Unable to fulfil the request due to " + ex);
	}

	
}