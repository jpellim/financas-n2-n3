package com.example.financasn2.exception;

public class DataEmissaoNaoAnteriorDataVencimentoException extends RuntimeException {
  
	private static final long serialVersionUID = 3893695212975129768L;

	public DataEmissaoNaoAnteriorDataVencimentoException(final String message) {
		super(message);
	}

	public DataEmissaoNaoAnteriorDataVencimentoException(final Throwable cause) {
		super(cause);
	}

	public DataEmissaoNaoAnteriorDataVencimentoException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
