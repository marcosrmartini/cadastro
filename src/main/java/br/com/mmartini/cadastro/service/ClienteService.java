package br.com.mmartini.cadastro.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import br.com.mmartini.cadastro.model.Cliente;

public interface ClienteService {

	public Cliente incluir(Cliente cliente);
	public Page<Cliente> pesquisaPorNome(String nome, int page, int size);
	public Cliente pesquisaPorCPF(String cpf);
	public Optional<Cliente> pesquisaPorId(Long id);
	public Cliente alterar(Cliente cliente);
	public Cliente removerCliente(Long id);
	public Cliente alterarParcial(Cliente cliente);

}
