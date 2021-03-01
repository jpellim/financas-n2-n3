package com.example.financasn2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financasn2.model.Ativo;
 

public interface AtivoRepository extends JpaRepository<Ativo, Long> {

	Ativo findByNome(String nome);
 
}
