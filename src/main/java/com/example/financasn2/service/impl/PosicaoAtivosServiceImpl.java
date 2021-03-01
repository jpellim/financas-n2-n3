package com.example.financasn2.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financasn2.enums.TipoLancamentoEnum;
import com.example.financasn2.exception.AtivoNaoEncontradoException;
import com.example.financasn2.model.Ativo;
import com.example.financasn2.model.ConsultaPosicaoAtivo;
import com.example.financasn2.model.Lancamento;
import com.example.financasn2.model.PosicaoAtivo;
import com.example.financasn2.repository.AtivoRepository;
import com.example.financasn2.repository.LancamentoRepository;
import com.example.financasn2.service.PosicaoAtivosService;

@Service
public class PosicaoAtivosServiceImpl implements PosicaoAtivosService {

	private final static Integer CASAS_DECIMAIS = 8;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private AtivoRepository ativoRepository;
  
	
	@Override
	public ConsultaPosicaoAtivo consultar(final ConsultaPosicaoAtivo consulta) {
 		
		List<Lancamento> lanctos = lancamentoRepository.findAll();

		List<PosicaoAtivo> posicoesAtivos = lanctos.stream()
				.filter(x -> x.getDataMovimento().equals(consulta.getDataPequisa()))
			    .collect(Collectors.groupingBy(
			        Lancamento::getAtivo,
			        Collectors.groupingBy(
			            Lancamento::getTipoAtivo,
			            Collectors.reducing(
			                BigDecimal.ZERO,
			                Lancamento::getQuantidadeLiquidaMovimentacaoByTipo,
			                BigDecimal::add))))
			    .entrySet()
			    .stream()
			    .flatMap(e1 -> e1.getValue()
			         .entrySet()
			         .stream()
			         .map(e2 -> new PosicaoAtivo(e1.getKey(), e2.getKey(),  e2.getValue())))
			    .collect(Collectors.toList());
		
		for (final PosicaoAtivo posicaoAtivo: posicoesAtivos) {
			final Ativo ativo = ativoRepository.findById(posicaoAtivo.getAtivo().getId()).orElseThrow(() -> new AtivoNaoEncontradoException(posicaoAtivo.getAtivo().getId()));
			posicaoAtivo.setDataMovimento(consulta.getDataPequisa());
			final BigDecimal valorMercadoTotal = posicaoAtivo.getQuantidadeTotal().multiply(ativo.getPrecoMercado()).setScale(CASAS_DECIMAIS, RoundingMode.HALF_DOWN);
			posicaoAtivo.setValorMercadoTotal(valorMercadoTotal);
			final BigDecimal rendimento = calcularRendimento(lanctos, ativo);
			posicaoAtivo.setRendimento(rendimento);
			final BigDecimal lucro = calcularLucro(lanctos, ativo);
			posicaoAtivo.setLucro(lucro);
			posicaoAtivo.setConsultaPosicaoAtivo(consulta);
		}
		  
		consulta.setPosicoesAtivos(posicoesAtivos);
		return consulta;
	}

	private BigDecimal calcularLucro(List<Lancamento> lanctos, Ativo ativo) {
		final BigDecimal valorCompras = lanctos.stream()
											   .filter(l -> ativo.getNome().equals(l.getNomeAtivo()) 
													        && l.getTipo().equals(TipoLancamentoEnum.ENTRADA))
											   .map(x -> x.getValorLiquidoMovimentacaoByTipo())
											   .reduce(BigDecimal.ZERO, BigDecimal::add); 
		final BigDecimal valorVendas = lanctos.stream()
											   .filter(l -> ativo.getNome().equals(l.getNomeAtivo()) 
													        && l.getTipo().equals(TipoLancamentoEnum.SAIDA))
											   .map(x -> x.getValorLiquidoMovimentacaoByTipo())
											   .reduce(BigDecimal.ZERO, BigDecimal::add); 
		final BigDecimal lucro = valorVendas.subtract(valorCompras).setScale(CASAS_DECIMAIS, RoundingMode.HALF_DOWN);
		return lucro;
		
	}

	private BigDecimal calcularRendimento(List<Lancamento> lanctos, Ativo ativo) {
		final BigDecimal valorCompras = lanctos.stream()
											   .filter(l -> ativo.getNome().equals(l.getNomeAtivo()) 
													        && l.getTipo().equals(TipoLancamentoEnum.ENTRADA))
											   .map(x -> x.getValorLiquidoMovimentacaoByTipo())
											   .reduce(BigDecimal.ZERO, BigDecimal::add); 
		final BigDecimal qtdeCompras = lanctos.stream()
											  .filter(l -> ativo.getNome().equals(l.getNomeAtivo()) 
													        && l.getTipo().equals(TipoLancamentoEnum.ENTRADA))
											  .map(x -> x.getQuantidade())
											  .reduce(BigDecimal.ZERO, BigDecimal::add); 
		final BigDecimal mediaCompras = valorCompras.divide(qtdeCompras, CASAS_DECIMAIS, RoundingMode.HALF_DOWN);
		final BigDecimal rendimento = ativo.getPrecoMercado().divide(mediaCompras, CASAS_DECIMAIS, RoundingMode.HALF_DOWN);
		return rendimento;
	}

 
}
