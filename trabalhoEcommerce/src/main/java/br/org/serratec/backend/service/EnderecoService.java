package br.org.serratec.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.org.serratec.backend.dto.EnderecoInserirDTO;
import br.org.serratec.backend.dto.EnderecoMostrarDTO;
import br.org.serratec.backend.model.Endereco;
import br.org.serratec.backend.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository enderecoRepository;

	public EnderecoMostrarDTO organizarCep(EnderecoInserirDTO enderecoInserirDTO) {
		Endereco endereco = enderecoRepository.findByCep(enderecoInserirDTO.getCep());
		if (endereco == null) {
			RestTemplate rs = new RestTemplate();
			String uri = "https://viacep.com.br/ws/" + enderecoInserirDTO.getCep() + "/json/";
			Optional<EnderecoMostrarDTO> enderecoViaCep = Optional
					.ofNullable(rs.getForObject(uri, EnderecoMostrarDTO.class));
			if (enderecoViaCep.get().getCep() != null) {
				// replaceAll - quando encontrar o traco ele tira e substitui por nada
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				enderecoViaCep.get().setNumero(enderecoInserirDTO.getNumero());
				/*
				 * enderecoViaCep.get().setLogradouro(uri); enderecoViaCep.get().setUf(uri);
				 * enderecoViaCep.get().setLogradouro(uri); enderecoViaCep.get().setBairro(uri);
				 * enderecoViaCep.get().setComplemento(uri);
				 */
				return inserir(enderecoViaCep.get());
			} 
				return new EnderecoMostrarDTO(endereco);
		}
		return null;
	}

	public EnderecoMostrarDTO inserir(EnderecoMostrarDTO enderecoMostrarDTO) {
		Endereco endereco = new Endereco();
		endereco.setBairro(enderecoMostrarDTO.getBairro());
		endereco.setCep(enderecoMostrarDTO.getCep());
		endereco.setCidade(enderecoMostrarDTO.getLocalidade());
		endereco.setComplemento(enderecoMostrarDTO.getComplemento());
		endereco.setEstado(enderecoMostrarDTO.getUf());
		endereco.setNumero(enderecoMostrarDTO.getNumero());
		enderecoRepository.save(endereco);
		return new EnderecoMostrarDTO(endereco);
	}
}
