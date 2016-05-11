package br.org.meuvoto.controller;

import java.util.Date;

public class MandatoVO {
	
	private Long idPessoa;
	private Long idCargo;
	private Date dataInicio;
	private Date dataFim;

	public Long getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Long pessoaId) {
		this.idPessoa = pessoaId;
	}
	public Long getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(Long cargoId) {
		this.idCargo = cargoId;
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
}