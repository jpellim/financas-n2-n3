package com.example.financasn2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.financasn2.model.MovimentacaoAtivo;
import com.example.financasn2.service.MovimentacaoAtivoService;


@RestController
@RequestMapping("/movimentacao") 
public class MovimentacaoController {

	@Autowired
	private MovimentacaoAtivoService movimentacaoAtivoService;
	
	@PostMapping("/comprar")
	public void comprar(@RequestBody final MovimentacaoAtivo movimentacaoAtivo) {
		movimentacaoAtivoService.comprar(movimentacaoAtivo); 
	}
	
	@PostMapping("/vender")
	public void vender(@RequestBody final MovimentacaoAtivo movimentacaoAtivo) {
		movimentacaoAtivoService.vender(movimentacaoAtivo); 
	}
}
