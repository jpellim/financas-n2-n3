package com.example.financasn2.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.example.financasn2.enums.TipoAtivoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class PosicaoAtivo {
   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_consulta_posicao")
	private ConsultaPosicaoAtivo consultaPosicaoAtivo;
	
	@NotNull
	@ManyToOne
    @JoinColumn(name = "id_ativo", nullable = false)
	private Ativo ativo;

	@NotNull  
	private LocalDate dataMovimento;
 	
	@NotNull
	private TipoAtivoEnum tipo;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer=6, fraction=2)
	private BigDecimal quantidadeTotal;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer=10, fraction=8)
	private BigDecimal valorMercadoTotal;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer=10, fraction=8)
	private BigDecimal rendimento;
	
	//@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer=10, fraction=8)
	private BigDecimal lucro;
	
	public PosicaoAtivo(Ativo ativo, TipoAtivoEnum tipo, BigDecimal quantidadeTotal) {
		this.ativo = ativo;
		this.tipo = tipo;
		this.quantidadeTotal = quantidadeTotal;
	}
		
}
