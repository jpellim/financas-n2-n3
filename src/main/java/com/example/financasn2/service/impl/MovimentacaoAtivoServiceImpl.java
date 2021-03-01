package com.example.financasn2.service.impl;

import static java.lang.String.format;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financasn2.exception.DataMovimentacaoEmFinalDeSemanaException;
import com.example.financasn2.exception.DataMovimentacaoNaoEstaEntreEmissaoAndVenctoException;
import com.example.financasn2.exception.QuantidadeDaVendaExcedeSaldoException;
import com.example.financasn2.model.Ativo;
import com.example.financasn2.model.ContaCorrente;
import com.example.financasn2.model.MovimentacaoAtivo;
import com.example.financasn2.service.AtivoService;
import com.example.financasn2.service.MovimentacaoAtivoService;
import com.example.financasn2.strategy.MovimentacaoStrategy;
import com.example.financasn2.strategy.MovimentacaoStrategyFactory;

@Service
public class MovimentacaoAtivoServiceImpl implements MovimentacaoAtivoService {

	@Autowired
	private com.example.financasn2.service.ContaCorrenteService contaCorrenteService;
	
	@Autowired
	private MovimentacaoStrategyFactory movimentacaoStrategyFactory;
	
	@Autowired
	private AtivoService ativoService;
	
	@Override
	public void comprar(final MovimentacaoAtivo movimentacaoAtivo) {
 
		final Ativo ativo = ativoService.getById(movimentacaoAtivo.getAtivo().getId());
		
		final MovimentacaoStrategy strategy = movimentacaoStrategyFactory.createStrategy(movimentacaoAtivo);
		
		validarDataMovimentacao(movimentacaoAtivo);
		
		strategy.efetuarMovimentacao(ativo, 
									 movimentacaoAtivo.getDataMovimento(),
								  	 movimentacaoAtivo.getQuantidade(), 
								  	 movimentacaoAtivo.getValorMovimentacao());
		
	}


	@Override
	public void vender(final MovimentacaoAtivo movimentacaoAtivo) {
	 
		final Ativo ativo = ativoService.getById(movimentacaoAtivo.getAtivo().getId());
		
		final MovimentacaoStrategy strategy = movimentacaoStrategyFactory.createStrategy(movimentacaoAtivo);
		 
		validarDataMovimentacao(movimentacaoAtivo);
		
		this.validarVenda(movimentacaoAtivo);
		
		strategy.efetuarMovimentacao(ativo, 
									 movimentacaoAtivo.getDataMovimento(),
								  	 movimentacaoAtivo.getQuantidade(), 
								  	 movimentacaoAtivo.getValorMovimentacao());
		
	}

	private void validarVenda(MovimentacaoAtivo movimentacaoAtivo) {

		final ContaCorrente contaCorrente = contaCorrenteService.consultarAteDate(movimentacaoAtivo.getDataMovimento());
		
		if (movimentacaoAtivo.getQuantidade().compareTo(contaCorrente.getQuantidadeTotal()) > 0) {
			throw new QuantidadeDaVendaExcedeSaldoException("Quantidade vendida é superior à quantidade total de ativos na data.");
		}
		
	}
	

	private void validarDataMovimentacao(MovimentacaoAtivo movimentacaoAtivo) {
 
		if (!(movimentacaoAtivo.getDataMovimento().compareTo(movimentacaoAtivo.getAtivo().getDataEmissao()) >= 0 
				&& movimentacaoAtivo.getDataMovimento().compareTo(movimentacaoAtivo.getAtivo().getDataVencimento()) < 0 )) {
			throw new DataMovimentacaoNaoEstaEntreEmissaoAndVenctoException(format("Data Movimentação %s Emissão %s e Vencimento %s",
					movimentacaoAtivo.getDataMovimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					movimentacaoAtivo.getAtivo().getDataEmissao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					movimentacaoAtivo.getAtivo().getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
		}
		
		if (movimentacaoAtivo.getDataMovimento().getDayOfWeek() == DayOfWeek.SATURDAY 
				|| movimentacaoAtivo.getDataMovimento().getDayOfWeek() == DayOfWeek.SUNDAY) {
			throw new DataMovimentacaoEmFinalDeSemanaException(format(movimentacaoAtivo.getDataMovimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
		}
		
	}
}
