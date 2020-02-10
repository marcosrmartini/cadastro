package br.com.mmartini.cadastro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.mmartini.cadastro.model.Cliente;
import br.com.mmartini.cadastro.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	public ClienteRepository repository;

	@Override
	public Optional<Cliente> pesquisaPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<Cliente> pesquisaPorNome(String nome, int page, int size) {
		PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nomeCompleto");
		return repository.pesquisaPorNome("%" + nome + "%", pageRequest);
	}

	@Override
	public Cliente pesquisaPorCPF(String cpf) {
		return repository.pesquisaPorCPF(cpf);
	}

	@Override
	public Cliente incluir(Cliente cliente) {
		return repository.save(cliente);
	}

	@Override
	public Cliente alterar(Cliente cliente) {
		return repository.save(cliente);
	}

	@Override
	public Cliente removerCliente(Long id) {
		Optional<Cliente> c = repository.findById(id);
		if (c.isPresent()) {
			repository.delete(c.get());
			return c.get();
		} else
			return new Cliente();
	}

	@Override
	public Cliente alterarParcial(Cliente cliente) {
		Optional<Cliente> c = repository.findById(cliente.getId());

		if (cliente.getNomeCompleto() != null) {
			c.get().setNomeCompleto(cliente.getNomeCompleto());
		}

		if (cliente.getCpf() != null) {
			c.get().setCpf(cliente.getCpf());
		}

		if (cliente.getDataNascimento() != null) {
			c.get().setDataNascimento(cliente.getDataNascimento());
		}

		return repository.save(c.get());
	}

}
