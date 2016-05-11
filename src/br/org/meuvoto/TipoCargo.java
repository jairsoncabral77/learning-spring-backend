package br.org.meuvoto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoCargo {
	
	@Id
	private Integer id;

	@Basic
	@Column(nullable=false, unique=true)
	private String nomeCargo;
	
	@Basic 
	private Integer duracao;
	
	public Integer getId() {
		return id;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}
	
}
