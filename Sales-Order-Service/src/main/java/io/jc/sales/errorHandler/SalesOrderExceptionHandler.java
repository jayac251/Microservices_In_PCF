package io.jc.sales.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jc.sales.errorHandler.OrderNotFoundException;
import io.jc.sales.errorHandler.SalesOrderException;

@RestControllerAdvice
public class SalesOrderExceptionHandler {
	@ExceptionHandler(value = { OrderNotFoundException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse orderNotFound(Exception ex) {
		return new ErrorResponse(404, "Sales Order Not found, " + ex.getMessage());
	}

	@ExceptionHandler(value = { SalesOrderException.class,Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse salesOrderError(Exception ex) {
		return new ErrorResponse(500, "Unable to fulfil the request due to " + ex);
	}

	
}