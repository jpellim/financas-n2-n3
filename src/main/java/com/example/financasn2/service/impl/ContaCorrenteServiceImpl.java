package com.example.financasn2.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financasn2.model.ContaCorrente;
import com.example.financasn2.model.Lancamento;
import com.example.financasn2.repository.LancamentoRepository;
import com.example.financasn2.service.ContaCorrenteService;

@Service
public class ContaCorrenteServiceImpl implements ContaCorrenteService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Override
	public ContaCorrente consultarByDate(final LocalDate data) {

		List<Lancamento> lanctos = lancamentoRepository.findAll();

		BigDecimal quantidadeTotal = lanctos.stream()
											.filter(x -> x.getDataMovimento().equals(data))
											.map(x -> x.getQuantidadeLiquidaMovimentacaoByTipo())
											.reduce(BigDecimal.ZERO, BigDecimal::add);

		BigDecimal saldo = lanctos.stream()
								  .filter(x -> x.getDataMovimento().equals(data))
								  .map(x -> x.getValorLiquidoMovimentacaoByTipo())
							      .reduce(BigDecimal.ZERO, BigDecimal::add);

		return ContaCorrente.builder()
							.quantidadeTotal(quantidadeTotal)
							.saldo(saldo)
							.build();

	}

	@Override
	public ContaCorrente consultarAteDate(LocalDate data) {

		List<Lancamento> lanctos = lancamentoRepository.findAll();

		BigDecimal quantidadeTotal = lanctos.stream()
											.filter(x -> x.getDataMovimento().compareTo(data) <= 0)
											.map(x -> x.getQuantidadeLiquidaMovimentacaoByTipo())
											.reduce(BigDecimal.ZERO, BigDecimal::add);

		BigDecimal saldo = lanctos.stream()
								  .filter(x -> x.getDataMovimento().compareTo(data) <= 0)
								  .map(x -> x.getValorLiquidoMovimentacaoByTipo())
							      .reduce(BigDecimal.ZERO, BigDecimal::add);

		return ContaCorrente.builder()
							.quantidadeTotal(quantidadeTotal)
							.saldo(saldo)
							.build();
	}
 

}
