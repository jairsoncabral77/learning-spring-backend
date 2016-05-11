package br.org.meuvoto.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.org.meuvoto.Mandato;
import br.org.meuvoto.Pessoa;

public interface MandatoRepository extends CrudRepository<Mandato, Long>{
	
	@Query(name="busca_mandato_com_intersecao_periodo")
	public List<Mandato> pesquisaMandatosDoPeriodo(Pessoa pessoa, Date dataInicio, Date dataFim);
	
}