package br.com.fiap.clientes.domain.service;

import br.com.fiap.clientes.api.model.ClienteDto;
import br.com.fiap.clientes.config.MessageConfig;
import br.com.fiap.clientes.domain.model.Cliente;
import br.com.fiap.clientes.domain.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final ModelMapper modelMapper;

    private final MessageConfig messageConfig;

    public List<ClienteDto> buscarClientePorNome(String nome) {
        var clienteList = clienteRepository.findByNomeIgnoreCaseContaining(nome);

        return clienteList.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDto.class))
                .toList();
    }

    public void add(ClienteDto clienteDto) {
        var cliente = modelMapper.map(clienteDto, Cliente.class);
        clienteRepository.save(cliente);
    }

    public ClienteDto update(ClienteDto clienteDto, Long id) {
        var optionalCliente = clienteRepository.findById(id);

        if(optionalCliente.isPresent()){
            var cliente = optionalCliente.get();
            modelMapper.map(clienteDto, cliente);

            cliente = clienteRepository.save(cliente);

            return modelMapper.map(cliente, ClienteDto.class);
        } else {
            throw new EntityNotFoundException(messageConfig.getClienteNaoEncontrado());
        }
    }

    public void delete(Long id) {
        var optionalCliente = clienteRepository.findById(id);

        if(optionalCliente.isPresent()){
            clienteRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(messageConfig.getClienteNaoEncontrado());
        }
    }

    public ClienteDto getClienteById(Long id) {
        var optionalCliente = clienteRepository.findById(id);

        if(optionalCliente.isPresent()){
            return modelMapper.map(optionalCliente.get(), ClienteDto.class);
        } else {
            throw new EntityNotFoundException(messageConfig.getClienteNaoEncontrado());
        }
    }

    public List<ClienteDto> findAll() {
        return clienteRepository.findAll().stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDto.class))
                .toList();
    }
}
