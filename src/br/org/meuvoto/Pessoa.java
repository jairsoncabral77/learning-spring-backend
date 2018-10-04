package br.org.meuvoto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.TableGenerator;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@TableGenerator(table="hibernate_sequence",name="pessoa",valueColumnName="sequence_next_hi_value", allocationSize=5)
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="pessoa")
	private Long id;
	
	@Column(nullable=false, updatable=false)
	@CPF(message="{validatedValue} não é um CPF válido")
	private String cpf;
	
	@Column(length=100, nullable=false)
	@Length(min=5,max=100,message="Nome precisa ter no mínimo {min} e no máximo {max} caracteres")
	private String nome;

	@PrePersist
	@PreUpdate
	@PostLoad
	public void validate() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		validator.validate(this);
	}
	
	public Pessoa comNomeCPF(String nome, String cpf) {
		this.setNome(nome);
		this.setCpf(cpf);
		return this;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
