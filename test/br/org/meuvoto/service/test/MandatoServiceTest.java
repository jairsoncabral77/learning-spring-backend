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
	public void createMandatoConflitante() throws Exception {
		mandatoService.create(2000L, 3000L, new Date());
	}

	@Test
	@DatabaseSetup("/testData.xml")
	public void createMandato() throws Exception {
		Mandato mandato = mandatoService.create(2000L, 3001L, new Date());
		Assert.assertNotNull(mandato);
		Assert.assertNotNull(mandato.getId());
		Calendar date = new GregorianCalendar(2022, 01, 01);
		mandato = mandatoService.create(2000L, 3000L, date.getTime());
		Assert.assertNotNull(mandato);
		Assert.assertNotNull(mandato.getId());
		Assert.assertFalse(mandatoService.obterMandatosPorPessoa("11111111111").isEmpty());
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
