package com.example.financasn2.exception;

public class QuantidadeDaVendaExcedeSaldoException extends RuntimeException {
	
	private static final long serialVersionUID = -3453766353790933374L;

	public QuantidadeDaVendaExcedeSaldoException(final String message) {
		super(message);
	}

	public QuantidadeDaVendaExcedeSaldoException(final Throwable cause) {
		super(cause);
	}

	public QuantidadeDaVendaExcedeSaldoException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
