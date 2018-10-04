package br.org.meuvoto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import br.org.meuvoto.TipoCargo;

@Transactional
public interface TipoCargoRepository extends CrudRepository<TipoCargo, Long>{
	
	public TipoCargo findByNomeCargo(String nome);
	
}