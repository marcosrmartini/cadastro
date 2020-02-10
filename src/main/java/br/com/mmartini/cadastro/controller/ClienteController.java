package br.com.mmartini.cadastro.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mmartini.cadastro.model.Cliente;
import br.com.mmartini.cadastro.payload.ClientePayload;
import br.com.mmartini.cadastro.service.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClienteService service;

	@PostMapping
	public ResponseEntity<Cliente> incluirCliente(@Valid @RequestBody ClientePayload c) {
		Cliente response = new Cliente();
		Cliente cliente = c.build();
		try {
			response = service.incluir(cliente);
			if (response.getId() == null || response.getId() == 0L) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			} else {
				return ResponseEntity.ok().body(response);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping
	public ResponseEntity<Cliente> alterarCliente(@Valid @RequestBody ClientePayload c) {
		Cliente response = new Cliente();
		Cliente cliente = c.build();
		try {
			response = service.alterar(cliente);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.notFound().build();
	}

	@PatchMapping
	public ResponseEntity<Cliente> alterarClienteParcial(@Valid @RequestBody ClientePayload c) {
		Cliente response = new Cliente();
		Cliente cliente = c.build();
		try {
			response = service.alterarParcial(cliente);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping(value = "/porNome/{nome}")
    public ResponseEntity<Page<Cliente>> pesquisaPorNome(@PathVariable("nome") String nome, 
    @RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
    @RequestParam(
            value = "size",
            required = false,
            defaultValue = "10") int size) {
        Page<Cliente> response;
        try {
            response = service.pesquisaPorNome(nome, page, size);
            if (response.isEmpty()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            } else {
                return ResponseEntity.ok().body(response);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.notFound().build();
    }

	@RequestMapping(value = "/porCPF/{cpf}")
	public ResponseEntity<Cliente> pesquisaPorCPF(@PathVariable("cpf") String cpf) {
		Cliente response = new Cliente();
		try {
			response = service.pesquisaPorCPF(cpf);
			if (response == null) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			} else {
				return ResponseEntity.ok().body(response);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping(value = "/porId/{id}")
	public ResponseEntity<Cliente> pesquisaPorId(@PathVariable("id") Long id) {
		Optional<Cliente> response;
		try {
			response = service.pesquisaPorId(id);
			if (!response.isPresent()) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			} else {
				return ResponseEntity.ok().body(response.get());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> removerCliente(@PathVariable("id") Long id) {
		Cliente response = new Cliente();
		try {
			response = service.removerCliente(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.notFound().build();
	}

}
