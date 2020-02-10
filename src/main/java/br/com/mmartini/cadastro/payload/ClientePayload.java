package br.com.mmartini.cadastro.payload;

import java.util.Date;

import br.com.mmartini.cadastro.model.Cliente;

public class ClientePayload {

	private Long id;
	private String nomeCompleto;
	private String cpf;
	private Date dataNascimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Cliente build()  {
		return new Cliente(this.getId(), this.getNomeCompleto(), this.getCpf(), this.getDataNascimento());
	}

}
