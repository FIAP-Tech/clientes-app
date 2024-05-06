package br.com.fiap.clientes.domain.model;

import lombok.Data;

@Data
public class MensagemEmail {

    private String emailDestinatario;

    private String assunto;

    private String corpoEmail;

    public void addCorpoEmail(String mensagem) {
        corpoEmail += mensagem + "/n";
    }
}
