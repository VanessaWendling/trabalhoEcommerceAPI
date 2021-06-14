package br.org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.backend.config.MailConfig;
import br.org.serratec.backend.dto.CadastroInserirDTO;
import br.org.serratec.backend.dto.CadastroMostrarDTO;
import br.org.serratec.backend.dto.ClienteMostrarDTO;
import br.org.serratec.backend.exception.EmailException;
import br.org.serratec.backend.model.Cliente;
import br.org.serratec.backend.model.Endereco;
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

	public CadastroMostrarDTO inserir(CadastroInserirDTO cadastroInserirDTO) throws EmailException {
		Cliente u = clienteRepository.findByEmail(cadastroInserirDTO.getEmail()); // irá fazer o Find para conferir se o email
																				// digitado já existe
		if (u != null) {
			throw new EmailException("Email já existente | Insira outro");
		}
		// setar as variáveis, a criptografia da senha e depois salvar os dados passados
		Cliente clienteSetar = new Cliente();
		Endereco endereco = new Endereco();
		clienteSetar.setNomeCompleto(cadastroInserirDTO.getNomeCompleto());
		clienteSetar.setNomeUsuario(cadastroInserirDTO.getNomeUsuario());
		clienteSetar.setEmail(cadastroInserirDTO.getEmail());
		clienteSetar.setCpf(cadastroInserirDTO.getCpf());
		clienteSetar.setSenha(bCryptPasswordEncoder.encode(cadastroInserirDTO.getSenha()));
		clienteSetar.setDataNasc(cadastroInserirDTO.getDataNasc());
		endereco.setCep(cadastroInserirDTO.getCep());
		//clienteSetar.setEnderecos(enderecoService.organizarCep(cadastroInserirDTO));
		//clienteSetar.setPedidos(clienteInserirDTO.getPedidos());
		clienteSetar = clienteRepository.save(clienteSetar);
		mailConfig.enviarEmail(cadastroInserirDTO.getEmail(), "Cadastro de Cliente", clienteSetar.toString());
		return new CadastroMostrarDTO(clienteSetar);
	}
}
