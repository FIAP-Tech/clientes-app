package br.com.fiap.clientes.api.model;

import br.com.fiap.clientes.domain.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private Endereco endereco;

    private LocalDate dataNascimento;

    private String cpf;

    private LocalDateTime dataCadastro;
}
