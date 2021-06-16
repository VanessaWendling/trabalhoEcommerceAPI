package br.org.serratec.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.dto.PedidoInserirDTO;
import br.org.serratec.backend.dto.PedidoMostrarDTO;
import br.org.serratec.backend.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<PedidoMostrarDTO>> listar() {
		List<PedidoMostrarDTO> pedidoDTOs = pedidoService.listar();
		return ResponseEntity.ok(pedidoDTOs);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoMostrarDTO inserir(@Valid @RequestBody PedidoInserirDTO pedidoInserirDTO) {
		return pedidoService.inserir(pedidoInserirDTO);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualizar determinado Pedido", notes = "Atualizar Pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pedido atualizado com sucesso"),
			@ApiResponse(code = 201, message = "Pedido criado com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<PedidoMostrarDTO> atualizar(@PathVariable Long id, @RequestBody PedidoInserirDTO pedidoInserirDTO) {
		PedidoMostrarDTO pedidoMostrarDTO = pedidoService.atualizar(id, pedidoInserirDTO);
		if (pedidoMostrarDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pedidoMostrarDTO);
	}
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletar determinada Pedido", notes = "Deletar Pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pedido deletada com sucesso"),
			@ApiResponse(code = 204, message = "Sem Conteúdo"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		if (!pedidoService.deletar(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
