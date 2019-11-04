package com.microservice.webservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
		//System.out.println("user not found exception");
		super(message);
		// TODO Auto-generated constructor stub
	}

}
