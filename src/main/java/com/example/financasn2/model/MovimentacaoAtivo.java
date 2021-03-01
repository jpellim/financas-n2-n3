package com.example.financasn2.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.financasn2.enums.TipoMovimentacaoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor 
public class MovimentacaoAtivo {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	 
	@NotNull
	@ManyToOne
    @JoinColumn(name = "id_ativo", nullable = false)
	private Ativo ativo;

	@NotNull
	private TipoMovimentacaoEnum tipo;
	
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataMovimento;

	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer=6, fraction=2)
	private BigDecimal quantidade;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer=10, fraction=8)
	private BigDecimal valorMovimentacao;
}
