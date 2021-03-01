package com.example.financasn2.service;

import java.time.LocalDate;

import com.example.financasn2.model.ContaCorrente;

public interface ContaCorrenteService {
 
	public ContaCorrente consultarByDate(LocalDate data);
	
	public ContaCorrente consultarAteDate(LocalDate data);
	 
	 
}
