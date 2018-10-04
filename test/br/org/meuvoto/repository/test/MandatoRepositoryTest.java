package br.org.meuvoto.repository.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import br.org.meuvoto.Cargo;
import br.org.meuvoto.Mandato;
import br.org.meuvoto.Pessoa;
import br.org.meuvoto.repository.CargoRepository;
import br.org.meuvoto.repository.MandatoRepository;
import br.org.meuvoto.repository.PessoaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans.xml" })
@TestExecutionListeners({ DbUnitTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
public class MandatoRepositoryTest {

	@Autowired
	MandatoRepository mandatoRepository;

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	CargoRepository cargoRepository;

	private Cargo cargo;

	private Pessoa pessoa;

	@Test
	@DatabaseSetup("/testData.xml")
	public void testCreate() {
		cargo = cargoRepository.findOne(2000L);
		pessoa = pessoaRepository.findOne(3000L);
		Calendar dataInicio = new GregorianCalendar();
		dataInicio.set(2016, 9, 1);
		Calendar dataFim = (Calendar) dataInicio.clone();
		dataFim.add(Calendar.YEAR, 4);
		Mandato mandato = mandatoRepository.save(new Mandato().aPartirDe(dataInicio.getTime()).ate(dataFim.getTime())
				.paraCargo(cargo).ocupadoPor(pessoa));
		Assert.assertNotNull(mandato.getId());
		Assert.assertNotNull(mandato.getCargo());
		Assert.assertEquals("SENADOR", mandato.getCargo().getTipo().getNomeCargo());
	}

}