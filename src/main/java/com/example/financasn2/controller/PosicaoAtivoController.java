package com.example.financasn2.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.financasn2.model.PosicaoAtivo;
import com.example.financasn2.service.ConsultaPosicaoAtivoService;


@RestController
@RequestMapping("/posicao-ativos") 
public class PosicaoAtivoController {
 
	@Autowired
	private ConsultaPosicaoAtivoService consultaPosicaoAtivoService;
	
	@GetMapping()
	public Long requisitarConsultaPosicaoAtivos(@RequestParam(name = "dataConsulta", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE) final LocalDate dataConsulta) {
		return consultaPosicaoAtivoService.registraNovConsulta(dataConsulta);
	}
	
	@GetMapping("/{numRequisicao}")
	public List<PosicaoAtivo> consultarPosicaoAtivos(@PathVariable(name = "numRequisicao", required = true) final Long numRequisicao) {
		return consultaPosicaoAtivoService.consultarPorNumRequisicao(numRequisicao);
		
	}
	
}
