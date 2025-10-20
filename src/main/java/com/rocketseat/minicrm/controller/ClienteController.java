package com.rocketseat.minicrm.controller;

import com.rocketseat.minicrm.domain.Cliente;
import com.rocketseat.minicrm.domain.Contato;
import com.rocketseat.minicrm.repository.ClienteRepository;
import com.rocketseat.minicrm.repository.ContatoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final ContatoRepository contatoRepository;

    public ClienteController(ClienteRepository clienteRepository, ContatoRepository contatoRepository) {
        this.clienteRepository = clienteRepository;
        this.contatoRepository = contatoRepository;
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente payload) {
        Cliente cliente = clienteRepository.save(payload);
        return ResponseEntity.created(URI.create("/clietes/"+cliente.getId())).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    @PostMapping("/{id}/contatos")
    public ResponseEntity<Contato> criarContatos(@PathVariable Long id, @RequestBody Contato payload) {
        var opt = clienteRepository.findById(id);
        if(opt.isEmpty()) return ResponseEntity.notFound().build();

        var cliente = opt.get();
        payload.setId(null);
        payload.setCliente(cliente);

        var salvo = contatoRepository.save(payload);
        return ResponseEntity.created(URI.create("/clientes"+id+"/contatos/"+salvo.getId())).body(salvo);
    }

    @GetMapping("/{id}/contatos")
    public ResponseEntity<List<Contato>> listarContatos(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(c -> ResponseEntity.ok(c.getContatos()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
