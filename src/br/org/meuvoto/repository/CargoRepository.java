package br.org.meuvoto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.meuvoto.Cargo;

@Transactional(readOnly=false,propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public interface CargoRepository extends CrudRepository<Cargo, Long>{
	
}