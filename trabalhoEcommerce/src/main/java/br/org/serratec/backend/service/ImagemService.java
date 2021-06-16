package br.org.serratec.backend.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.backend.model.Imagem;
import br.org.serratec.backend.model.Produto;
import br.org.serratec.backend.repository.ImagemRepository;

@Service
public class ImagemService {

	@Autowired
	private ImagemRepository imagemRepository;
	
	public Imagem inserir(Produto produto, MultipartFile file) throws IOException { //essa interface MultiPartFile recebe um arquivo
		Imagem imagem = new Imagem();
		imagem.setDados(file.getBytes());
		imagem.setTipo(file.getContentType());
		imagem.setNome(file.getName());
		imagem.setProduto(produto);
		return imagemRepository.save(imagem);
	}
	
	public Imagem buscar(Long id) {
		Optional<Imagem> imagem = imagemRepository.findById(id);
		
		if (!imagem.isPresent()) {
			return null;
		}
		return imagem.get();
	} 
	
}