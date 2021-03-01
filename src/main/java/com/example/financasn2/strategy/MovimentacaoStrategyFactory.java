package com.example.financasn2.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.financasn2.enums.TipoMovimentacaoEnum;
import com.example.financasn2.model.MovimentacaoAtivo;

@Component
public class MovimentacaoStrategyFactory {

	
    @Autowired
    @Qualifier("compraStrategy")
    private CompraStrategy compraStrategy;

    @Autowired
    @Qualifier("vendaStrategy")
    private VendaStrategy vendaStrategy;
	
 
	public MovimentacaoStrategy createStrategy(MovimentacaoAtivo movimentacao) {

		if (movimentacao.getTipo() == TipoMovimentacaoEnum.COMPRA) {
			return compraStrategy;
		}

		if (movimentacao.getTipo() == TipoMovimentacaoEnum.VENDA) {
			return vendaStrategy;
		}

		return null; // lancar excecao

	}
}
