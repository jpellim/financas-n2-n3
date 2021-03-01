package com.example.financasn2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financasn2.model.Lancamento;
 

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
  
}
