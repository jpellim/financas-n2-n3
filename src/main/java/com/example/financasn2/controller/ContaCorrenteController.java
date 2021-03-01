package com.example.financasn2.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.financasn2.model.ContaCorrente;
import com.example.financasn2.service.ContaCorrenteService;


@RestController
@RequestMapping("/conta-corrente") 
public class ContaCorrenteController {

	@Autowired
	private ContaCorrenteService contaCorrenteService;
	
	@GetMapping()
	public ContaCorrente consultar(@RequestParam(name = "dataConsulta", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE) final LocalDate dataConsulta) {
		return contaCorrenteService.consultarByDate(dataConsulta); 
	}
}
