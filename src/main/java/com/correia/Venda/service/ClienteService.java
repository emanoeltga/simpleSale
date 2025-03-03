package com.correia.Venda.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.correia.Venda.dto.ClienteDTO;
import com.correia.Venda.model.Cliente;
import com.correia.Venda.repository.ClienteRepository;
import com.correia.Venda.repository.ItemVendaRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ItemVendaRepository itemRepository;

	public List<ClienteDTO> listarClientes() {
		return clienteRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public ClienteDTO buscarClientePorId(Long id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		return convertToDTO(cliente);
	}

	@Transactional
	public ClienteDTO salvarCliente(ClienteDTO clienteDTO) {
		Cliente cliente = convertToEntity(clienteDTO);
		cliente = clienteRepository.save(cliente);
		return convertToDTO(cliente);
	}

	@Transactional
	public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		cliente.setNome(clienteDTO.getNome());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setEndereco(clienteDTO.getEndereco());
		cliente.setTelefone(clienteDTO.getTelefone());
		cliente = clienteRepository.save(cliente);
		return convertToDTO(cliente);
	}

	@Transactional
	public boolean deletarCliente(Long id) {
		if (clienteRepository.existsById(id)) {
			try {
				clienteRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println(e.getMessage());
				return false;
			}

		} else
			return false;
	}

	private ClienteDTO convertToDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		// BeanUtils.copyProperties(cliente, clienteDTO);

		clienteDTO.setId(cliente.getCliente_id());
		clienteDTO.setNome(cliente.getNome());
		clienteDTO.setCpf(cliente.getCpf());
		clienteDTO.setEndereco(cliente.getEndereco());
		clienteDTO.setTelefone(cliente.getTelefone());

		return clienteDTO;
	}

	private Cliente convertToEntity(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		//BeanUtils.copyProperties(clienteDTO, cliente);

		cliente.setCliente_id(clienteDTO.getId());
		cliente.setNome(clienteDTO.getNome());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setEndereco(clienteDTO.getEndereco());
		cliente.setTelefone(clienteDTO.getTelefone());

		return cliente;
	}
}