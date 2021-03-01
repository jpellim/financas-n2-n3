package com.example.financasn2.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder 
public class ContaCorrente {
 
	  
	@NotNull  
	private LocalDate dataMovimento;
 	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer=6, fraction=2)
	private BigDecimal quantidadeTotal;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer=10, fraction=8)
	private BigDecimal saldo;
}
