package br.org.meuvoto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoCargo {
	
	@Id
	private Long id;

	@Basic
	@Column(nullable=false, unique=true)
	private String nomeCargo;
	
	@Basic 
	private int duracao;
	
	public Long getId() {
		return id;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	
}
