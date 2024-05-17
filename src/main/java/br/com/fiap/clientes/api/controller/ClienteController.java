package br.com.fiap.clientes.api.controller;

import br.com.fiap.clientes.api.model.ClienteDto;
import br.com.fiap.clientes.domain.service.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public List<ClienteDto> buscarClientePorNome(@RequestParam(required = false) String nome) {
        if(Objects.isNull(nome)){
            return service.findAll();
        }else{
            return service.buscarClientePorNome(nome);
        }

    }

    @GetMapping("/{id}")
    public ClienteDto getClienteById(@PathVariable Long id) {
        return service.getClienteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid ClienteDto clienteDto) {
        service.add(clienteDto);
    }

    @PutMapping("/{id}")
    public ClienteDto update(@RequestBody @Valid ClienteDto clienteDto, @PathVariable("id") Long id){
        return service.update(clienteDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable("id") Long id){
        service.delete(id);
    }
}
