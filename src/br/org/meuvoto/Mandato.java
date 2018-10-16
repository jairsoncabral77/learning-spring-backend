package br.org.meuvoto;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

import br.org.meuvoto.commons.Periodico;
import br.org.meuvoto.commons.validator.Periodo;

@NamedQueries({ 
	@NamedQuery(name = "busca_mandato_com_intersecao_periodo", query = "SELECT m FROM Mandato m WHERE "
		+ "(m.pessoa = ?1) AND (" + "(?2 BETWEEN m.dataInicio AND m.dataFim) OR "
		+ "(?3 BETWEEN m.dataInicio AND m.dataFim))"),
	@NamedQuery(name = "busca_cargo_ocupado_no_periodo", query = "SELECT m FROM Mandato m WHERE "
		+ "(m.cargo = ?1) AND (" + "(?2 BETWEEN m.dataInicio AND m.dataFim) OR "
		+ "(?3 BETWEEN m.dataInicio AND m.dataFim))") })
@Entity
@Periodo(tamanhoMinimo = 1, timeUnit = TimeUnit.DAYS, message = "A data final do Mandato precisa no mínimo um dia após a data inicial")
@TableGenerator(table="hibernate_sequence",name="mandato",valueColumnName="sequence_next_hi_value", allocationSize=5)
public class Mandato implements Periodico {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="mandato")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	@NotNull(message = "mandato precisa estar associado a um pessoa")
	private Pessoa pessoa;

	@Temporal(TemporalType.DATE)
	@Basic(optional = false)
	@Column(nullable = false)
	@NotNull(message = "data de início do mandato não pode ser nula")
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	@Basic(optional = true)
	@Column(nullable = true)
	private Date dataFim;

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	@NotNull(message = "mandato precisa estar associado a um cargo")
	private Cargo cargo;

	@PrePersist
	@PreUpdate
	@PostLoad
	public void validate() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		validator.validate(this);
	}

	public Long getId() {
		return id;
	}

	public Mandato paraCargo(Cargo cargo) {
		this.setCargo(cargo);
		return this;
	}

	public Mandato aPartirDe(Date dataInicio) {
		this.setDataInicio(dataInicio);
		return this;
	}

	public Mandato ate(Date dataFim) {
		this.setDataFim(dataFim);
		return this;
	}

	public Mandato ocupadoPor(Pessoa pessoa) {
		this.setPessoa(pessoa);
		return this;
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
