package br.org.meuvoto.repository.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import br.org.meuvoto.Cargo;
import br.org.meuvoto.TipoCargo;
import br.org.meuvoto.repository.CargoRepository;
import br.org.meuvoto.repository.TipoCargoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"/test-beans.xml"})
@TestExecutionListeners({ DbUnitTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class })
@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
public class CargoRepositoryTest {

	@Autowired
	CargoRepository cargoRepository;

	@Autowired
	TipoCargoRepository tipoCargoRepository;
	
	@Test
	@DatabaseSetup("/testData.xml")
	public void createCargo() {
		TipoCargo tipo1 = tipoCargoRepository.findOne(1000L);
		TipoCargo tipo2 = tipoCargoRepository.findOne(1001L);
		Assert.assertNotNull(tipo1);
		Assert.assertNotNull(tipo2);
		Cargo cargoSenador = cargoRepository.save(new Cargo().doTipo(tipo1).paraUnidadeParlamentar("SENADO FEDERAL PB1"));
		Cargo cargoDeputado = cargoRepository.save(new Cargo().doTipo(tipo2).paraUnidadeParlamentar("DEPUTADO FEDERAL PB1"));
		Assert.assertNotNull(cargoSenador.getId());
		Assert.assertNotNull(cargoDeputado.getId());
	}

}