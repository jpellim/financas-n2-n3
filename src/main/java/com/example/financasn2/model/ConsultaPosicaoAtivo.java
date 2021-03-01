package com.example.financasn2.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConsultaPosicaoAtivo {
   
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     
    private LocalDate dataPequisa;
    
    private String completo;
	
	@OneToMany(mappedBy = "consultaPosicaoAtivo", fetch = FetchType.LAZY)
    List<PosicaoAtivo> posicoesAtivos;
  
}
