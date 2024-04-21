package br.com.fiap.clientes.dados;

import br.com.fiap.clientes.api.model.ClienteDto;
import br.com.fiap.clientes.domain.model.Cliente;

public class ClienteDados {

    public Cliente criarCliente1(){
        return Cliente.builder()
                .id(1L)
                .nome("Bruno Silveira")
                .build();
    }

    public Cliente criarCliente2(){
        return Cliente.builder()
                .id(1L)
                .nome("Bruno Otávio")
                .build();
    }

    public ClienteDto criarClienteDto1(){
        return ClienteDto.builder()
                .id(1L)
                .nome("Bruno Silveira")
                .build();
    }

    public ClienteDto criarClienteDto2(){
        return ClienteDto.builder()
                .id(1L)
                .nome("Bruno Otávio")
                .build();
    }
}
