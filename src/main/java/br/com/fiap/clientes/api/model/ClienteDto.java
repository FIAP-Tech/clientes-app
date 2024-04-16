package br.com.fiap.clientes.api.model;

import br.com.fiap.clientes.domain.model.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private Endereco endereco;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String cpf;
}
