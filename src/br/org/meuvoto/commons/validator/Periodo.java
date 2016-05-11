package br.org.meuvoto.commons.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validador de período. O Objeto a ser validado precisa ter os campos
 * dataFinal e dataInicial não nulos.
 * 
 * @see PeriodoValidator
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {PeriodoValidator.class})
public @interface Periodo {
	
	String message() default "dataFim deve ser após dataInicio";

	/**
	 * Menor distância entre dataFinal e dataInicial;
	 */
	int tamanhoMinimo() default 1;
	
	/**
	 * Unidade de tempo usada para expressar o tempoMinimo
	 */
	TimeUnit timeUnit() default TimeUnit.DAYS;
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
