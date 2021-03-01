package com.example.financasn2.exception;

public class InfrastructureException extends RuntimeException {
 
	private static final long serialVersionUID = 4902373399839927487L;

	public InfrastructureException(final String message) {
		super(message);
	}
	
	public InfrastructureException(final Throwable cause) {
		super(cause);
	}

	public InfrastructureException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
