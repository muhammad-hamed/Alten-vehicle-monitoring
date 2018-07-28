package com.alten.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFountException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

}
