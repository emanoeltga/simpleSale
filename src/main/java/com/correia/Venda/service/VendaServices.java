package com.correia.Venda.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.correia.Venda.dto.ItemVendaDTO;
import com.correia.Venda.dto.VendaDTO;
import com.correia.Venda.exception.ClienteNotFoundException;
import com.correia.Venda.exception.VendaNotFoundException;
import com.correia.Venda.model.Cliente;
import com.correia.Venda.model.ItemPK;
import com.correia.Venda.model.ItemVenda;
import com.correia.Venda.model.Produto;
import com.correia.Venda.model.Venda;
import com.correia.Venda.repository.ClienteRepository;
import com.correia.Venda.repository.ItemVendaRepository;
import com.correia.Venda.repository.ProdutoRepository;
import com.correia.Venda.repository.VendaRepository;

import jakarta.transaction.Transactional;

@Service
public class VendaServices {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ItemVendaRepository itemVendaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	/**
	 * Cria uma nova venda para um cliente.
	 *
	 * @param clienteId ID do cliente.
	 * @return A venda criada.
	 */
	@Transactional
	public Venda save(VendaDTO vendaDTO) {
		Cliente cliente = clienteRepository.findById(vendaDTO.getCliente())
				.orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));

		Venda venda = new Venda();
		if (vendaDTO.getVendaId()!= null) {
			if (vendaRepository.existsById(vendaDTO.getVendaId())) {
				//throw new VendaNotFoundException("");
				venda.setId(vendaDTO.getVendaId());
			}
		}
		
		venda.setCliente(cliente);
		venda.setDataVenda(new Date());
		venda=vendaRepository.save(venda);
		
		for (ItemVendaDTO item : vendaDTO.getItens()) {
			Produto produto = item.getProduto().convert(item.getProduto());
			/*Produto produto = produtoRepository.findById(item.getProduto().getProduto_id())
					.orElseThrow(() -> new ProdutoNotFoundException("Cliente não encontrado"));*/
			
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setId( new ItemPK(venda, produto, Long.valueOf(vendaDTO.getItens().indexOf(item) +1)));
			itemVenda.setAcrescimo(item.getAcrescimo());
			itemVenda.setDesconto(item.getDesconto());
			itemVenda.setQuantidade(item.getQuantidade());
			itemVenda.setValorUnitario(item.getValorUnitario());

			venda.adicionarItem(itemVendaRepository.save(itemVenda));
			
		}

		return vendaRepository.save(venda);
	}


	/**
	 * Finaliza a venda e persiste no banco de dados.
	 *
	 * @param vendaId ID da venda.
	 * @return Venda
	 */
	public VendaDTO findById(Long vendaId) {
		Venda venda = vendaRepository.findById(vendaId)
				.orElseThrow(() -> new VendaNotFoundException("Venda não encontrada"));		
		return venda.converte(venda);
	}
	
	public List<VendaDTO> findAll() {
	    return vendaRepository.findAll().stream().map(venda -> {
	        VendaDTO dto = new VendaDTO();
	        // Copia as propriedades de venda para dto
	        dto.setCliente(venda.getCliente().getCliente_id());
	        dto.setVendaId(venda.getId());
	        
	        
	        // Se você tiver uma lista de itens e quiser copiá-los também, você pode fazer isso
	        List<ItemVendaDTO> itemDTOs = venda.getItens().stream().map(itemVenda -> {
	            ItemVendaDTO itemDTO = new ItemVendaDTO();
	            
	            itemDTO.setProduto(itemVenda.getId().getProduto().convert(itemVenda.getId().getProduto()));
	            itemDTO.setVenda(itemVenda.getId().getVenda().getId());
	            itemDTO.setQuantidade(itemVenda.getQuantidade());
	            itemDTO.setAcrescimo(itemVenda.getAcrescimo());
	            itemDTO.setDesconto(itemVenda.getDesconto());
	            itemDTO.setValorUnitario(itemVenda.getValorUnitario());
	            itemDTO.setNseq(itemVenda.getId().getSequencial());
	            
	            // Se você quiser apenas o ID do produto, por exemplo
	            //itemDTO.setProduto(itemVenda.getId().getProduto().getProduto_id());
	            return itemDTO;
	        }).collect(Collectors.toList());
	        
	        dto.setItens(itemDTOs); // Define a lista de itens no DTO
	        return dto;
	    }).collect(Collectors.toList());
	}
	
	@Transactional
	public Venda deleteById(Long vendaId) {
		Venda venda = vendaRepository.findById(vendaId)
				.orElseThrow(() -> new VendaNotFoundException("Venda não encontrada"));
		vendaRepository.delete(venda);
		return venda;
	}
}