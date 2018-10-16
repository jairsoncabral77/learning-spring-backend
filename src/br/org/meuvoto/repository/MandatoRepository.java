package br.org.meuvoto.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.meuvoto.Cargo;
import br.org.meuvoto.Mandato;
import br.org.meuvoto.Pessoa;

@Transactional(readOnly=false,propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public interface MandatoRepository extends CrudRepository<Mandato, Long> {
	
	@Query(name="busca_mandato_com_intersecao_periodo")
	public List<Mandato> pesquisaMandatosDoPeriodo(Pessoa pessoa, Date dataInicio, Date dataFim);

	@Query(name="busca_cargo_ocupado_no_periodo")
	public List<Mandato> pesquisaMandatosDoPeriodo(Cargo cargo, Date dataInicio, Date dataFim);

	public List<Mandato> findByPessoa(Pessoa pessoa);

	public List<Mandato> findByPessoaCpf(String cpf);

}