package br.org.meuvoto.controller;

import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.org.meuvoto.Mandato;
import br.org.meuvoto.exception.ObjetoNaoEncontradoException;
import br.org.meuvoto.repository.MandatoRepository;
import br.org.meuvoto.service.MandatoService;

// TESTABILITY; 3; 2; "Definir arquitetura de testes para controllers REST"
@RestController
public class MandatoRestController {

	@Autowired
	private MandatoRepository mandatoRepository;

	@Autowired
	private MandatoService mandatoService;

	//TESTABILITY; 2; 5; "Testar Rest controller MandatoRestController.get"
	@RequestMapping(value = "/mandato/{id:[\\d]+}", produces = { "application/json" }, method=RequestMethod.GET)
	public Mandato get(@PathVariable("id") Long id) {
		Mandato mandato = mandatoRepository.findOne(id);
		if ( mandato == null ) {
			throw new ObjetoNaoEncontradoException();
		}
		return mandato;
	}

	//TESTABILITY; 2; 5; "Testar Rest controller MandatoRestController.getAll"
	@RequestMapping(value = "/mandato", produces = { "application/json" }, method=RequestMethod.GET)
	public List<Mandato> getAll() {
		Iterable<Mandato> result = mandatoRepository.findAll();
		@SuppressWarnings("unchecked")
		List<Mandato> listResult = IteratorUtils.toList(result.iterator());
		return listResult;
	}

	//TESTABILITY; 2; 5; "Testar Rest controller MandatoRestController.create"
	@RequestMapping(value = "/mandato", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public Mandato create(@RequestBody MandatoVO mandatoVO) throws Exception {
		Mandato mandato = mandatoService.create(mandatoVO.getIdCargo(), mandatoVO.getIdPessoa(), mandatoVO.getDataInicio());
		return mandato;
	}

}
