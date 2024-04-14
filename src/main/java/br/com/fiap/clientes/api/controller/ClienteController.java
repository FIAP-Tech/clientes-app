package br.com.fiap.clientes.api.controller;

import br.com.fiap.clientes.api.model.ClienteDto;
import br.com.fiap.clientes.domain.model.Cliente;
import br.com.fiap.clientes.domain.service.ClienteService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public List<Cliente> getAll() {
        return service.getAll();
    }

    @GetMapping("/{nome}")
    public List<ClienteDto> getClienteByNome(@PathVariable String nome) {
        return service.getClienteByNome(nome);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @NotNull ClienteDto clienteDto) {
        service.add(clienteDto);
    }

    @PutMapping("/{id}")
    public ClienteDto update(@RequestBody @NotNull ClienteDto clienteDto, @PathVariable("id") Long id){
        return service.update(clienteDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable("id") Long id){
        service.delete(id);
    }
}
