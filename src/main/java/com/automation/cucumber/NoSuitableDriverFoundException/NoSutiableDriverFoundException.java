package com.automation.cucumber.NoSuitableDriverFoundException;

public class NoSutiableDriverFoundException extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;

	public NoSutiableDriverFoundException(String message) {
		super(message);
	}
	
	public NoSutiableDriverFoundException(){
		this("");
	}

}
