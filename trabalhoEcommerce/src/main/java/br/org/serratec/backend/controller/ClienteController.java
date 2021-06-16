package br.org.serratec.backend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.backend.dto.ClienteInserirDTO;
import br.org.serratec.backend.dto.ClienteMostrarDTO;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping ("/clientes")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	@ApiOperation(value = "Listar Clientes", notes = "Listar Clientes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Clientes listadas com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<List<ClienteMostrarDTO>> listar(){
		List<ClienteMostrarDTO> clientes = clienteService.listar();
		return ResponseEntity.ok(clientes);
	}
	@PostMapping
	public ResponseEntity<Object> inserir (@RequestBody ClienteInserirDTO cadastroInserirDTO){
		try {
			ClienteMostrarDTO dto = clienteService.inserir(cadastroInserirDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(dto.getId()).toUri();
			return ResponseEntity.created(uri).body(dto);
		}catch (EmailException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualizar determinado Cliente", notes = "Atualizar Cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cliente atualizado com sucesso"),
			@ApiResponse(code = 201, message = "Cliente criado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<ClienteMostrarDTO> atualizar(@PathVariable Long id, @RequestBody ClienteInserirDTO clienteInserirDTO) {
		ClienteMostrarDTO clienteMostrarDTO = clienteService.atualizar(id, clienteInserirDTO);
		if (clienteMostrarDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clienteMostrarDTO);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar determinada Categoria", notes = "Deletar Categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Categoria deletada com sucesso"),
			@ApiResponse(code = 204, message = "Sem Conteúdo"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		if (!clienteService.deletar(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
