package com.example.financasn2.strategy;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.financasn2.enums.TipoLancamentoEnum;
import com.example.financasn2.model.Ativo;
import com.example.financasn2.model.Lancamento;
import com.example.financasn2.repository.LancamentoRepository;

@Component
@Qualifier("compraStrategy")
public class CompraStrategy implements MovimentacaoStrategy {

	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	
	@Override
	public void efetuarMovimentacao(Ativo ativo, LocalDate dataMovimento, BigDecimal quantidade, BigDecimal valorMovimentacao) {
		
		final Lancamento lanc = Lancamento.builder()
									.descricao("LANÇAMENTO DE COMPRA")
									.tipo(TipoLancamentoEnum.ENTRADA)
									.ativo(ativo)
									.dataMovimento(dataMovimento)
									.quantidade(quantidade)
									.valorMovimentacao(valorMovimentacao)
									.build();
		
		lancamentoRepository.save(lanc);
		
	}

}
