package io.jc.item.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ItemExceptionHandler {
	@ExceptionHandler(value = { ItemNotFoundException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse itemNotFound(Exception ex) {
		return new ErrorResponse(404, "Item Not found, " + ex.getMessage());
	}

	@ExceptionHandler(value = { ItemException.class,Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse itemError(Exception ex) {
		return new ErrorResponse(500, "Unable to fulfil the request due to " + ex);
	}

	
}