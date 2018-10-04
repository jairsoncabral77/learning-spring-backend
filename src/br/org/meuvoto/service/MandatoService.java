package br.org.meuvoto.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.meuvoto.Cargo;
import br.org.meuvoto.Mandato;
import br.org.meuvoto.Pessoa;
import br.org.meuvoto.exception.ConflitoPeriodoMandatoException;
import br.org.meuvoto.exception.DependenciaNaoEncontradaException;
import br.org.meuvoto.repository.CargoRepository;
import br.org.meuvoto.repository.MandatoRepository;
import br.org.meuvoto.repository.PessoaRepository;

@Service
@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
public class MandatoService {
	
	@Autowired
	private MandatoRepository mandatoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CargoRepository cargoRepository;
	
	/* TESTABILITY; 1; 2; "criar teste automatizado para pesquisaMandatosComPeriodoConflitante" */
	public List<Mandato> pesquisaMandatosComPeriodoConflitante(Mandato mandato) {
		List<Mandato> conflitantes = mandatoRepository.pesquisaMandatosDoPeriodo(
				mandato.getPessoa(), mandato.getDataInicio(), mandato.getDataFim());
		return conflitantes; 
	}

	public Mandato create(Long idCargo, Long idPessoa, Date dataInicio) throws Exception {
		Logger.getLogger(this.getClass()).debug(String.format("Criando mandato para cargo=%1$d, pessoa=%2%d, dataInicio=%3$tF",idCargo,idPessoa,dataInicio));
		Mandato mandato =  new Mandato();
		Pessoa pessoa = pessoaRepository.findOne(idPessoa);
		if ( pessoa == null ) {
			String message = String.format("A Pessoa (id=%d) designada para o mandato não foi encontrada", idPessoa);
			Logger.getLogger(this.getClass()).error(message);
			throw new DependenciaNaoEncontradaException(message);
		}
		mandato.setPessoa(pessoa);
		Cargo cargo = cargoRepository.findOne(idCargo);
		if ( cargo == null ) {
			String message = String.format("O Cargo (id=%d) informado não foi encontrado", idCargo);
			Logger.getLogger(this.getClass()).error(message);
			throw new DependenciaNaoEncontradaException(message);
		}
		mandato.setCargo(cargo);
		mandato.setDataInicio(dataInicio);
		Calendar dataFim = new GregorianCalendar();
		dataFim.setTime(dataInicio);
		dataFim.add(Calendar.MONTH,  cargo.getTipo().getDuracao());
		mandato.setDataFim(dataFim.getTime());
		if ( pesquisaMandatosComPeriodoConflitante(mandato).size() > 0 ) {
			String message = String.format("%s [id:%d] já exerce um mandato no período de %s a %s", 
					pessoa.getNome(), pessoa.getId(), SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT).format(dataInicio), 
					SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT).format(dataFim.getTime()));
			Logger.getLogger(this.getClass()).error(message);
			throw new ConflitoPeriodoMandatoException(message);
		}
		mandato = mandatoRepository.save(mandato);
		Logger.getLogger(this.getClass()).debug(String.format("Mandato criado para cargo=%d, pessoa=%d, dataInicio=%tF",idCargo,idPessoa,dataInicio));
		return mandato;
	}
	
	public List<Mandato> obterMandatosPorPessoa(@CPF String cpf) {
		return mandatoRepository.findByPessoaCpf(cpf);
	}

}