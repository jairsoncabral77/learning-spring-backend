package br.org.meuvoto.commons.validator;

import java.util.concurrent.TimeUnit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.org.meuvoto.commons.Periodico;

public class PeriodoValidator implements ConstraintValidator<Periodo, Periodico> {

	private int tamanho;
	private TimeUnit timeUnit;
	
	@Override
	public void initialize(Periodo dateInterval) {
		this.tamanho = dateInterval.tamanhoMinimo();
		this.timeUnit = dateInterval.timeUnit();
	}

	// TESTABILITY; 1; 2; "criar testes para o PeriodoValidator"
	@Override
	public boolean isValid(Periodico target, ConstraintValidatorContext arg1) {
		if ( target.getDataFim() == null || target.getDataInicio() == null ) {
			
		}
		return  target.getDataFim() != null &&
				target.getDataInicio() != null &&
				(target.getDataFim().getTime() - target.getDataInicio().getTime()) > timeUnit.toMillis(tamanho);
	}

}
