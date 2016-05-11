package br.org.meuvoto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Basic(optional=false)
	@Column(nullable=false, updatable=false)
	@CPF(message="{validatedValue} não é um CPF válido")
	private String cpf;
	
	@Basic(optional=false)
	@Column(length=100, nullable=false)
	@Length(min=5,max=100,message="Nome precisa ter no mínimo {min} e no máximo {max} caracteres")
	private String nome;

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
