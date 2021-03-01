package com.example.financasn2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.financasn2.enums.TipoAtivoEnum;
import com.example.financasn2.enums.TipoLancamentoEnum;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Lancamento {
	
	private final static Integer CASAS_DECIMAIS = 8;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotEmpty
	private String descricao;

	@NotNull
	@ManyToOne
    @JoinColumn(name = "id_ativo", nullable = false)
	private Ativo ativo;

	@NotNull
	private TipoLancamentoEnum tipo;
	
	@NotNull 
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataMovimento;

	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 6, fraction = 2)
	private BigDecimal quantidade;

	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 10, fraction = 8)
	private BigDecimal valorMovimentacao;

	public BigDecimal getValorLiquidoMovimentacaoByTipo() {
		
		if (tipo == TipoLancamentoEnum.ENTRADA) {
			return this.valorMovimentacao.multiply(this.quantidade).setScale(CASAS_DECIMAIS, RoundingMode.HALF_DOWN);
		}
		if (tipo == TipoLancamentoEnum.SAIDA) {
			return this.valorMovimentacao.multiply(this.quantidade).multiply(new BigDecimal(-1)).setScale(CASAS_DECIMAIS, RoundingMode.HALF_DOWN);
		}
		return null;
	}
	
	public BigDecimal getQuantidadeLiquidaMovimentacaoByTipo() {
		
		if (tipo == TipoLancamentoEnum.ENTRADA) {
			return this.quantidade;
		}
		if (tipo == TipoLancamentoEnum.SAIDA) {
			return this.quantidade.multiply(new BigDecimal(-1));
		}
		return null;
	}

//	public BigDecimal getValorTotal() {
//		return this.quantidade.multiply(this.valorMovimentacao).setScale(CASAS_DECIMAIS, RoundingMode.HALF_DOWN);
//	}
	
	public String getNomeAtivo() {
		return ativo.getNome();
	}
	
	public TipoAtivoEnum getTipoAtivo() {
		return ativo.getTipo();
	}
	
}
