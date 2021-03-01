package com.example.financasn2.service.impl;

import static java.lang.String.format;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financasn2.exception.DataEmissaoNaoAnteriorDataVencimentoException;
import com.example.financasn2.model.Ativo;
import com.example.financasn2.repository.AtivoRepository;
import com.example.financasn2.service.AtivoService;

@Service
public class AtivoServiceImpl implements AtivoService {

	@Autowired
	private AtivoRepository ativoRepository;

	@Override
	public void create(Ativo ativo) {
		validarDatas(ativo);
		ativoRepository.save(ativo);
	}

	@Override
	public void update(Ativo ativo) {
		validarDatas(ativo);
		ativoRepository.save(ativo);
	}

	@Override
	public List<Ativo> getAll() {
		return ativoRepository.findAll();
	}

	@Override
	public Ativo getById(Long idAtivo) {
		return ativoRepository.getOne(idAtivo);
	}

	@Override
	public void delete(Long id) {
		ativoRepository.deleteById(id);
	}

	private void validarDatas(Ativo ativo) {

		if (ativo.getDataVencimento().compareTo(ativo.getDataEmissao()) >= 0) {
			throw new DataEmissaoNaoAnteriorDataVencimentoException(format("Data Emissão %s e Vencimento %s",
					ativo.getDataEmissao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					ativo.getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
		}
	}

}
