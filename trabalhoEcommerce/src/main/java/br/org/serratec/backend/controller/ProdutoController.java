package br.org.serratec.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.backend.dto.ProdutoInserirDTO;
import br.org.serratec.backend.dto.ProdutoMostrarDTO;
import br.org.serratec.backend.service.ImagemService;
import br.org.serratec.backend.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ImagemService imagemService;

	/*
	@PostMapping
    @ResponseStatus (HttpStatus.CREATED)
	public ProdutoMostrarDTO inserir(@RequestParam MultipartFile file, @RequestPart Produto produto) throws IOException{
        return produtoService.inserir(produto, file);

	}
	
	@GetMapping ("/{id}/imagem")
    public ResponseEntity<byte[]> buscarPorFoto (@PathVariable Long id){
        Imagem imagem = imagemService.buscar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", imagem.getTipo());
        headers.add("content-length", String.valueOf(imagem.getDados().length));
        return new ResponseEntity<>(imagem.getDados(), headers, HttpStatus.OK);
    }
    
    @GetMapping
	public ResponseEntity<List<ProdutoMostrarDTO>> listar() {
		List<ProdutoMostrarDTO> produtoDTOs = produtoService.listar();
		return ResponseEntity.ok(produtoDTOs);
	}
    
    
    @GetMapping("/{id}")
	public ResponseEntity<ProdutoMostrarDTO> buscarProduto(@PathVariable Long id) {
		ProdutoMostrarDTO produtoDTO =  produtoService.buscar(id);
		return ResponseEntity.ok(produtoDTO);
	}*/
	
	@PostMapping
	public ProdutoMostrarDTO inserir (@Valid @RequestBody ProdutoInserirDTO produtoInserirDTO){
		ProdutoMostrarDTO dto = produtoService.inserir(produtoInserirDTO);
		return dto;
	}
}