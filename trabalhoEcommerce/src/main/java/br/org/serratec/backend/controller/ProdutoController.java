package br.org.serratec.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.backend.dto.ProdutoMostrarDTO;
import br.org.serratec.backend.model.Imagem;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.service.ImagemService;
import br.org.serratec.backend.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ImagemService imagemService;

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
	}
}