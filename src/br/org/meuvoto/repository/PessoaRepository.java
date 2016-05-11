package br.org.meuvoto.repository;

import org.springframework.data.repository.CrudRepository;

import br.org.meuvoto.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long>{
	
}