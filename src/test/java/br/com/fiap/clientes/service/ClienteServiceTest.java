package br.com.fiap.clientes.service;

import br.com.fiap.clientes.api.model.ClienteDto;
import br.com.fiap.clientes.dados.ClienteDados;
import br.com.fiap.clientes.domain.model.Cliente;
import br.com.fiap.clientes.domain.repository.ClienteRepository;
import br.com.fiap.clientes.domain.service.ClienteService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClienteServiceTest extends ClienteDados {

    private AutoCloseable closeable;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Nested
    class buscarClientePorNome {

        @Test
        void testBuscarClientePorNome_ComSucesso() {
            // Given
            var nome = "Bruno";
            var cliente1 = criarCliente1();
            var cliente2 = criarCliente2();
            var clienteList = Arrays.asList(cliente1, cliente2);

            when(clienteRepository.findByNomeIgnoreCaseContaining(nome)).thenReturn(clienteList);

            var clienteDto1 = criarClienteDto1();
            var clienteDto2 = criarClienteDto2();

            when(modelMapper.map(cliente1, ClienteDto.class)).thenReturn(clienteDto1);
            when(modelMapper.map(cliente2, ClienteDto.class)).thenReturn(clienteDto2);

            // When
            var result = clienteService.buscarClientePorNome(nome);

            // Then
            assertEquals(2, result.size());
            assertEquals(clienteDto1, result.get(0));
            assertEquals(clienteDto2, result.get(1));

            // Verify interactions
            verify(clienteRepository, times(1)).findByNomeIgnoreCaseContaining(nome);
            verify(modelMapper, times(1)).map(cliente1, ClienteDto.class);
            verify(modelMapper, times(1)).map(cliente2, ClienteDto.class);
        }

        @Test
        void testBuscarClientePorNome_SemResultados() {
            // Given
            var nome = "Non-existent";
            List<Cliente> clienteList = List.of();

            when(clienteRepository.findByNomeIgnoreCaseContaining(nome)).thenReturn(clienteList);

            // When
            var result = clienteService.buscarClientePorNome(nome);

            // Then
            assertEquals(0, result.size());

            // Verify interactions
            verify(clienteRepository, times(1)).findByNomeIgnoreCaseContaining(nome);
            verifyNoInteractions(modelMapper); // Ensure modelMapper was not called
        }
    }
}
