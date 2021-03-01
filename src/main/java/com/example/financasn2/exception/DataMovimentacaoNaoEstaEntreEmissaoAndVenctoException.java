package com.example.financasn2.exception;

public class DataMovimentacaoNaoEstaEntreEmissaoAndVenctoException extends RuntimeException {
  
	private static final long serialVersionUID = 3699435502098859767L;

	public DataMovimentacaoNaoEstaEntreEmissaoAndVenctoException(final String message) {
		super(message);
	}

	public DataMovimentacaoNaoEstaEntreEmissaoAndVenctoException(final Throwable cause) {
		super(cause);
	}

	public DataMovimentacaoNaoEstaEntreEmissaoAndVenctoException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
