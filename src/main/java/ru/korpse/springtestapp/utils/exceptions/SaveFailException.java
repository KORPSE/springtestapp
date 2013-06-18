package ru.korpse.springtestapp.utils.exceptions;

public class SaveFailException extends RuntimeException {

	private static final long serialVersionUID = -1687695558215842229L;

	public SaveFailException(String message) {
		super(message);
	}

}
