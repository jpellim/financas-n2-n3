package com.example.financasn2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.financasn2.model.Ativo;
import com.example.financasn2.service.AtivoService;


@RestController
@RequestMapping("/ativo") 
public class AtivoController {

	@Autowired
	private AtivoService ativoService;
	
	@GetMapping()
	public List<Ativo> getAll() {
		return ativoService.getAll();
	}
	
	@PostMapping()
	public void create(@RequestBody final Ativo ativo) {
		ativoService.create(ativo);
	}
	
	@DeleteMapping()
	public void delete(@RequestBody final Long id) {
		ativoService.delete(id);
	}
}
