package br.org.meuvoto.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.meuvoto.Cargo;
import br.org.meuvoto.Mandato;
import br.org.meuvoto.Pessoa;
import br.org.meuvoto.exception.ConflitoPeriodoMandatoException;
import br.org.meuvoto.exception.DependenciaNaoEncontradaException;
import br.org.meuvoto.repository.CargoRepository;
import br.org.meuvoto.repository.MandatoRepository;
import br.org.meuvoto.repository.PessoaRepository;

@Service
public class MandatoService {
	
	@Autowired
	private MandatoRepository mandatoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CargoRepository cargoRepository;
	
	/* TESTABILITY; 1; 2; "criar teste automatizado para pesquisaMandatosComPeriodoConflitante" */
	public Mandato pesquisaMandatosComPeriodoConflitante(Mandato mandato) {
		List<Mandato> conflitantes = mandatoRepository.pesquisaMandatosDoPeriodo(
				mandato.getPessoa(), mandato.getDataInicio(), mandato.getDataFim());
		return conflitantes.size() > 0? conflitantes.get(0): null; 
	}

	/* TESTABILITY; 1; 2; "criar teste automatizado para criação de mandato"*/
	@Transactional(value=TxType.REQUIRED)
	public Mandato create(Long idCargo, Long idPessoa, Date dataInicio, Date dataFim) throws Exception {
		Mandato mandato =  new Mandato();
		Pessoa pessoa = pessoaRepository.findOne(idPessoa);
		if ( pessoa == null ) {
			String message = String.format("A Pessoa (id=%d) designada para o mandato não foi encontrada", idPessoa);
			throw new DependenciaNaoEncontradaException(message);
		}
		mandato.setPessoa(pessoa);
		Cargo cargo = cargoRepository.findOne(idCargo);
		if ( cargo == null ) {
			String message = String.format("O Cargo (id=%d) informado não foi encontrado", idCargo);
			throw new DependenciaNaoEncontradaException(message);
		}
		mandato.setCargo(cargo);
		mandato.setDataInicio(dataInicio);
		mandato.setDataFim(dataFim);
		List<Mandato> conflitantes = mandatoRepository.pesquisaMandatosDoPeriodo(
				mandato.getPessoa(), mandato.getDataInicio(), mandato.getDataFim());
		if ( conflitantes.size() > 0 ) {
			String message = String.format("%s [id:%d] já possui registro de mandato mandato no período de %s a %s", 
					pessoa.getNome(), pessoa.getId(), SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT).format(dataInicio), 
					SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT).format(dataFim));
			throw new ConflitoPeriodoMandatoException(message);
		}
		return mandatoRepository.save(mandato);
	}

}