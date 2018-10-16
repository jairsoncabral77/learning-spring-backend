package br.org.meuvoto.service.test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import br.org.meuvoto.Mandato;
import br.org.meuvoto.exception.ConflitoPeriodoMandatoException;
import br.org.meuvoto.exception.DependenciaNaoEncontradaException;
import br.org.meuvoto.service.MandatoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans.xml" })
@TestExecutionListeners({ DbUnitTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@Transactional
public class MandatoServiceTest {

	@Autowired
	private MandatoService mandatoService;

	@Test(expected = ConflitoPeriodoMandatoException.class)
	@DatabaseSetup("/testData.xml")
	public void createMandatoPessoaConflitante() throws Exception {
		mandatoService.create(2000L, 3001L, new GregorianCalendar(2018,02,01).getTime());
	}
	
	@Test(expected = ConflitoPeriodoMandatoException.class)
	@DatabaseSetup("/testData.xml")
	public void createMandatoCargoConflitante() throws Exception {
		mandatoService.create(2001L, 3000L, new GregorianCalendar(2018,02,01).getTime());
	}

	@Test
	@DatabaseSetup("/testData.xml")
	public void obterMandatosPorCPF() throws Exception {
		Assert.assertFalse(mandatoService.obterMandatosPorPessoa("11111111111").isEmpty());
	}

	
	@Test
	@DatabaseSetup("/testData.xml")
	public void createMandato() throws Exception {
		Calendar dataInicio = new GregorianCalendar(2022, 01, 01);
		Mandato mandato = mandatoService.create(2001L, 3001L, dataInicio.getTime());
		Assert.assertNotNull(mandato);
		Assert.assertNotNull(mandato.getId());
	}
	
	@Test(expected = DependenciaNaoEncontradaException.class)
	public void createMandatoSemPessoa() throws Exception { 
		mandatoService.create(2000L, 0L, new Date());
	}

	@Test(expected = DependenciaNaoEncontradaException.class)
	public void createMandatoSemCargo() throws Exception { 
		mandatoService.create(0L, 3001L, new Date());
	}
	
}
