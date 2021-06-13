package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.config.MailConfig;
import br.org.serratec.backend.dto.ClienteInserirDTO;
import br.org.serratec.backend.dto.ClienteMostrarDTO;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MailConfig mailConfig;

	public List<ClienteMostrarDTO> listar() {
		List<ClienteMostrarDTO> clienteDTOs = new ArrayList<ClienteMostrarDTO>();
		List<Cliente> clientes = clienteRepository.findAll();
		for (Cliente cliente : clientes) {
			ClienteMostrarDTO dto = new ClienteMostrarDTO(cliente);
			clienteDTOs.add(dto);
		}
		return clienteDTOs;
	}

	public ClienteMostrarDTO inserir(ClienteInserirDTO clienteInserirDTO) throws EmailException {
		Cliente u = clienteRepository.findByEmail(clienteInserirDTO.getEmail()); // irá fazer o Find para conferir se o email
																				// digitado já existe
		if (u != null) {
			throw new EmailException("Email já existente | Insira outro");
		}
		// setar as variáveis, a criptografia da senha e depois salvar os dados passados
		Cliente clienteSetar = new Cliente();
		clienteSetar.setNomeCompleto(clienteInserirDTO.getNomeCompleto());
		clienteSetar.setNomeUsuario(clienteInserirDTO.getNomeUsuario());
		clienteSetar.setEmail(clienteInserirDTO.getEmail());
		clienteSetar.setCpf(clienteInserirDTO.getCpf());
		clienteSetar.setSenha(bCryptPasswordEncoder.encode(clienteInserirDTO.getSenha()));
		clienteSetar.setDataNasc(clienteInserirDTO.getDataNasc());
		//clienteSetar.setEnderecos(clienteInserirDTO.getEnderecos());
		//clienteSetar.setPedidos(clienteInserirDTO.getPedidos());
		clienteSetar = clienteRepository.save(clienteSetar);
		mailConfig.enviarEmail(clienteInserirDTO.getEmail(), "Cadastro de Cliente", clienteSetar.toString());
		return new ClienteMostrarDTO(clienteSetar);
	}
}
