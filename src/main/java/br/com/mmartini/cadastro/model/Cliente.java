package br.com.mmartini.cadastro.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import br.com.mmartini.cadastro.utils.Util;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "{nomeCompleto.not.blank}")
	private String nomeCompleto;
	@NotBlank(message = "{cpf.not.blank}")
	private String cpf;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@Transient
	private int idade;

	public Cliente() {
		super();
	}

	public Cliente(Long id, @NotBlank(message = "{nomeCompleto.not.blank}") String nomeCompleto,
			@NotBlank(message = "{cpf.not.blank}") String cpf, Date dataNascimento) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

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

	public int getIdade() {
		idade = 0;
		if (this.dataNascimento != null) {
			idade = Util.calculaIdade(this.dataNascimento);
		}
		return idade;
	}

}
