package br.org.serratec.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.dto.EnderecoInserirDTO;
import br.org.serratec.backend.dto.EnderecoMostrarDTO;
import br.org.serratec.backend.service.EnderecoService;

@RestController
@RequestMapping ("/enderecos")
public class EnderecoController {
	@Autowired
	private EnderecoService enderecoService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public EnderecoMostrarDTO inserir (@RequestBody EnderecoInserirDTO enderecoInserirDTO){
		EnderecoMostrarDTO dto = enderecoService.organizarCep(enderecoInserirDTO);
		return dto;
	}
	
	/*
	public ResponseEntity<EnderecoMostrarDTO> buscar (@RequestBody EnderecoInserirDTO enderecoInserirDTO){
		 
		EnderecoMostrarDTO enderecoMostrarDTO = enderecoService.organizarCep(enderecoInserirDTO);
		if (enderecoMostrarDTO == null) {
			return ResponseEntity.notFound().build();
		}else
			return ResponseEntity.ok(enderecoMostrarDTO);
}*/
}
