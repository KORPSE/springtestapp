package ru.korpse.springtestapp.utils.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -91666687733255146L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
