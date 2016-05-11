package br.org.meuvoto;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.org.meuvoto.commons.Periodico;
import br.org.meuvoto.commons.validator.Periodo;

@NamedQueries({
	@NamedQuery(name="busca_mandato_com_intersecao_periodo", 
		query="SELECT m FROM Mandato m, Pessoa p WHERE "
				+ "(p = ?1) AND ("
				+ "(?2 BETWEEN m.dataInicio AND m.dataFim) OR "
				+ "(?3 BETWEEN m.dataInicio AND m.dataFim))"
	)	
})

@Entity
@Periodo(tamanhoMinimo=1, timeUnit=TimeUnit.DAYS, 
	message="A data final do Mandato precisa no mínimo um dia após a data inicial")
public class Mandato implements Periodico {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional=false,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false)
	@NotNull(message="mandato precisa estar associado a um pessoa")
	private Pessoa pessoa;
	
	@Temporal(TemporalType.DATE)
	@Basic(optional=false)
	@Column(nullable=false)
	@NotNull(message="data de início do mandato não pode ser nula")
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	@Basic(optional=false)
	@Column(nullable=false)
	@NotNull(message="data fim do mandato não pode ser nula")
	private Date dataFim;
	
	@ManyToOne(optional=false,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false)
	@NotNull(message="mandato precisa estar associado a um cargo")
	private Cargo cargo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
