package com.correia.Venda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.correia.Venda.dto.VendaDTO;
import com.correia.Venda.model.Venda;
import com.correia.Venda.repository.ClienteRepository;
import com.correia.Venda.repository.ItemVendaRepository;
import com.correia.Venda.repository.ProdutoRepository;
import com.correia.Venda.service.VendaServices;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaServices vendaService;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @GetMapping
    public List<VendaDTO> buscarVendas() {        
    	
    	return vendaService.findAll();
    }

    // GET - Buscar uma venda por ID
    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> buscarVendaPorId(@PathVariable Long id) {
        vendaService.findById(id);
        return ResponseEntity.ok(vendaService.findById(id)); //venda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    // POST - Criar uma nova venda com itens
    @PostMapping
    public ResponseEntity<Venda> criarVenda(@RequestBody VendaDTO venda) {        
        return ResponseEntity.ok(vendaService.save(venda));
    }

    // PUT - Atualizar uma venda ou seus itens
    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizarVenda(@PathVariable Long id, @RequestBody VendaDTO vendaAtualizada) {
    	return ResponseEntity.ok(vendaService.save(vendaAtualizada));
    }

    // DELETE - Excluir uma venda e seus itens
    @DeleteMapping("/{id}")
    public ResponseEntity<Venda> excluirVenda(@PathVariable Long id) {
        //Venda venda = new Venda();
        //venda= vendaRepository.findById(id).get();
        
    	return ResponseEntity.ok(vendaService.deleteById(id));
    }
}
