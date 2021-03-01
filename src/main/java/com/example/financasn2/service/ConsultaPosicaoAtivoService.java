package com.example.financasn2.service;

import java.time.LocalDate;
import java.util.List;

import com.example.financasn2.model.PosicaoAtivo;

public interface ConsultaPosicaoAtivoService {
 
	public void consultarPosicoesAtivos();
	
	public Long registraNovConsulta(LocalDate dataPesquisa);
	 
	public List<PosicaoAtivo> consultarPorNumRequisicao(Long numRequisicao);
	
}
