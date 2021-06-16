package br.org.serratec.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.dto.EnderecoInserirDTO;
import br.org.serratec.backend.dto.EnderecoMostrarDTO;
import br.org.serratec.backend.service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping ("/enderecos")
public class EnderecoController {
	@Autowired
	private EnderecoService enderecoService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public EnderecoMostrarDTO inserir (@RequestBody EnderecoInserirDTO enderecoInserirDTO){
		EnderecoMostrarDTO dto = enderecoService.buscar(enderecoInserirDTO.getCep());
		return dto;
	}
	
	@GetMapping("{cep}")
    public ResponseEntity<EnderecoMostrarDTO> buscar(@PathVariable String cep) {
        EnderecoMostrarDTO enderecoDTO = enderecoService.buscar(cep);
        if (enderecoDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(enderecoDTO);
        }
    }
	@GetMapping
	@ApiOperation(value = "Listar Endereços", notes = "Listar Endereços")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Endereços listadas com sucesso"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso Indisponivel"),
			@ApiResponse(code = 500, message = "Erros interno do servidor"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção") })
	public ResponseEntity<List<EnderecoMostrarDTO>> listar(){
		List<EnderecoMostrarDTO> enderecos = enderecoService.listar();
		return ResponseEntity.ok(enderecos);
	}
}
