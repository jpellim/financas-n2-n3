package com.example.financasn2.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.financasn2.exception.InfrastructureException;
import com.example.financasn2.model.ConsultaPosicaoAtivo;
import com.example.financasn2.model.PosicaoAtivo;
import com.example.financasn2.repository.ConsultaPosicaoAtivoRepository;
import com.example.financasn2.repository.PosicaoAtivoRepository;
import com.example.financasn2.service.ConsultaPosicaoAtivoService;
import com.example.financasn2.service.PosicaoAtivosService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsultaPosicaoAtivoServiceImpl implements ConsultaPosicaoAtivoService {

	@Autowired
	private ConsultaPosicaoAtivoRepository consultaPosicaoAtivoRepository;
 
	@Autowired
	private PosicaoAtivoRepository posicaoAtivoRepository;
	
	@Autowired
	private PosicaoAtivosService posicaoAtivosService;
	

 	@Scheduled(fixedRate = 30000)
	@Override
	public void consultarPosicoesAtivos() {
	 
		List<ConsultaPosicaoAtivo> consultas = consultaPosicaoAtivoRepository.findAll();
		
		final ExecutorService executor = Executors.newFixedThreadPool(10);
 
		final List<CompletableFuture<ConsultaPosicaoAtivo>> completables = new ArrayList<>();
 
		for (final ConsultaPosicaoAtivo consulta: consultas) {
	 
			final CompletableFuture<ConsultaPosicaoAtivo> completableFuture = new CompletableFuture<>();
	
			final Future<Object> future = executor.submit(() -> {
	
				try {
	  				
					ConsultaPosicaoAtivo consultaPosicaoAtivo = posicaoAtivosService.consultar(consulta);
	
					completableFuture.complete(consultaPosicaoAtivo);
	
				} catch (Throwable e) {
					log.error("Erro no processamento da consulta da posição de ativo", e);
					throw new InfrastructureException(e.getMessage(), e);
				}
				return null;
			});
	
			try {
				future.get();
			} catch (Throwable e) {
				log.error("Erro no processamento da consulta da posição de ativo", e);
				throw new InfrastructureException(e.getMessage(), e);
			}
	
			completables.add(completableFuture);
			
		}
		
		for (CompletableFuture<ConsultaPosicaoAtivo> completableFuture : completables) {
			ConsultaPosicaoAtivo result = null;
			try {
				 
				result = completableFuture.get(); 
				 
				Optional<ConsultaPosicaoAtivo> posicao = consultaPosicaoAtivoRepository.findById(result.getId());
				
				if (posicao.isPresent()) {
					List<PosicaoAtivo> posicoesAtivosExistentes = posicaoAtivoRepository.findByConsultaPosicaoAtivo(posicao.get());
					for (PosicaoAtivo posicaoAtivo: posicoesAtivosExistentes) {
						posicaoAtivoRepository.deleteById(posicaoAtivo.getId());
					}
				}
				
			 
				for (PosicaoAtivo posicaoAtivo: result.getPosicoesAtivos()) {
					posicaoAtivoRepository.save(posicaoAtivo);
				}
				
				result.setCompleto("S");
				
				consultaPosicaoAtivoRepository.save(result);
				
			} catch (Throwable e) {
				String message = "Erro ao executar a consulta de posição de ativo - Mensagem: "
						+ e.getMessage();
				throw new InfrastructureException(message, e);
			}
		}
		 
	}

	@Override
	public Long registraNovConsulta(LocalDate dataPesquisa) {

		final ConsultaPosicaoAtivo consulta = new ConsultaPosicaoAtivo();
		consulta.setDataPequisa(dataPesquisa);
		consulta.setCompleto("N");
		
		ConsultaPosicaoAtivo consultaGerada = consultaPosicaoAtivoRepository.save(consulta);

		return consultaGerada.getId();
	}

	@Override
	public List<PosicaoAtivo> consultarPorNumRequisicao(Long numRequisicao) {
		 
	 	Optional<ConsultaPosicaoAtivo> consultaPosicaoAtivoOpt = consultaPosicaoAtivoRepository.findById(numRequisicao);
		
	 	if (!consultaPosicaoAtivoOpt.isPresent()) {
	 		return new ArrayList<>();
	 	}
	 	
	 	return posicaoAtivoRepository.findByConsultaPosicaoAtivo(consultaPosicaoAtivoOpt.get());
	 	 
	}
	

}
