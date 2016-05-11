package br.org.meuvoto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cargo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	// MANTAINABILITY; 1; 1; "converter para classe UnidadeParlamentar"
	// RELIABILITY; 1; 2; "incluir validadores de unidade parlamentar"
	@Basic(optional=false)
	@Column(length=100)
	private String unidade;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private TipoCargo tipo;

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
