package com.example.financasn2.exception;

public class DataMovimentacaoEmFinalDeSemanaException extends RuntimeException {
  
	private static final long serialVersionUID = 3699435502098859767L;

	public DataMovimentacaoEmFinalDeSemanaException(final String message) {
		super(message);
	}

	public DataMovimentacaoEmFinalDeSemanaException(final Throwable cause) {
		super(cause);
	}

	public DataMovimentacaoEmFinalDeSemanaException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
