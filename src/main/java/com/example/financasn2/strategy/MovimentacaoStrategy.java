package com.example.financasn2.strategy;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.financasn2.model.Ativo;

public interface MovimentacaoStrategy {

	public void efetuarMovimentacao(Ativo ativo, LocalDate dataMovimento, BigDecimal quantidade, BigDecimal valorMovimentacao);
	
}
