package com.example.financasn2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.financasn2.model.ConsultaPosicaoAtivo;
import com.example.financasn2.model.PosicaoAtivo;
 

public interface PosicaoAtivoRepository extends JpaRepository<PosicaoAtivo, Long> {
  
	List<PosicaoAtivo> findByConsultaPosicaoAtivo(ConsultaPosicaoAtivo consulta);

}
