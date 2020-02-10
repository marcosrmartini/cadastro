package br.com.mmartini.cadastro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mmartini.cadastro.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("select c from Cliente c where upper(c.nomeCompleto) like upper(:nome)")
	public Page<Cliente> pesquisaPorNome(@Param("nome") String nome, Pageable pageable);

	@Query("select c from Cliente c where c.cpf = :cpf")
	public Cliente pesquisaPorCPF(@Param("cpf") String cpf);

}
