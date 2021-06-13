package br.org.serratec.backend.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.backend.dto.ClienteInserirDTO;
import br.org.serratec.backend.dto.ClienteMostrarDTO;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.service.ClienteService;

@RestController
@RequestMapping ("/clientes")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<Object> inserir (@RequestBody ClienteInserirDTO clienteInserirDTO){
		try {
			ClienteMostrarDTO dto = clienteService.inserir(clienteInserirDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(dto.getId()).toUri();
			return ResponseEntity.created(uri).body(dto);
		}catch (EmailException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
}