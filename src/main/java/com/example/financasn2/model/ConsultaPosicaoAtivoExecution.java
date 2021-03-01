package com.example.financasn2.model;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaPosicaoAtivoExecution {

	private ConsultaPosicaoAtivo consultaPosicaoAtivo;
	private CompletableFuture<List<PosicaoAtivo>> futureConsultaResponse;
//	private Exception exception;
	
	public ConsultaPosicaoAtivoExecution(ConsultaPosicaoAtivo consultaPosicaoAtivo, CompletableFuture<List<PosicaoAtivo>> futureConsultaResponse) {
		this.consultaPosicaoAtivo = consultaPosicaoAtivo;
		this.futureConsultaResponse = futureConsultaResponse;
	}

 
 
}