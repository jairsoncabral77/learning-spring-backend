package br.org.meuvoto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.TableGenerator;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Entity
@TableGenerator(table="hibernate_sequence",name="cargo",valueColumnName="sequence_next_hi_value", allocationSize=5)
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="cargo")
	private Long id;

	@Column(length=100)
	private String unidade;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private TipoCargo tipo;

	@PrePersist
	@PreUpdate
	@PostLoad
	public void validate() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		validator.validate(this);
	}
	
	public Cargo doTipo(TipoCargo tipoCargo) {
		this.setTipo(tipoCargo);
		return this;
	}
	
	public Cargo paraUnidadeParlamentar(String unidadeParlamentar) {
		this.setUnidade(unidadeParlamentar);;
		return this;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public TipoCargo getTipo() {
		return tipo;
	}

	public void setTipo(TipoCargo tipo) {
		this.tipo = tipo;
	}
	
}
