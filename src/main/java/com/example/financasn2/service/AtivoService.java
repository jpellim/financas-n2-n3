package com.example.financasn2.service;

import java.util.List;

import com.example.financasn2.model.Ativo;

public interface AtivoService {

	public void create(Ativo ativo);
	
	public void update(Ativo ativo);
	
	public List<Ativo> getAll();
	
	public Ativo getById(Long id);
	
	public void delete(Long id);
}
