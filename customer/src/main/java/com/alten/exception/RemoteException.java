package com.alten.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RemoteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4867868555422603576L;

}
