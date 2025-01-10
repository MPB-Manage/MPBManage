package dk.mpb.manage.project.service;

import dk.mpb.manage.project.dto.ClientRequest;
import dk.mpb.manage.project.entity.Client;
import dk.mpb.manage.project.entity.Property;
import dk.mpb.manage.project.repository.ClientRepository;
import dk.mpb.manage.project.repository.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    ClientRepository clientRepository;
    @Mock
    PropertyRepository propertyRepository;

    ClientService clientService;

    @BeforeEach
    void setUp() {
        clientService = new ClientService(clientRepository, propertyRepository);
    }

    @Test
    void testGetAllClientForProperty() {
        Property property = new Property("testProperty");
        Client client = new Client(12345678, "testClient", "email", "description", property);
        when(propertyRepository.findByUserAccountName("testProperty")).thenReturn(Optional.of(property));
        when(clientRepository.findAllByPropertyId(property.getId())).thenReturn(List.of(client));
        assertEquals(1, clientService.getAllClientForProperty("testProperty").size());
    }

    @Test
    void testAddClientForProperty() {
        Property property = new Property("testProperty");
        when(propertyRepository.findByUserAccountName("testProperty")).thenReturn(Optional.of(property));
        ClientRequest cr = new ClientRequest(12345678, "testClient", "email", "description");
        Client client = new Client(12345678, "testClient", "email", "description", property);
        when(clientRepository.findAllByPropertyId(property.getId())).thenReturn(List.of(client));
        clientService.addClientForProperty("testProperty", cr);
        verify(clientRepository).save(any(Client.class));
        assertEquals(1, clientRepository.findAllByPropertyId(property.getId()).size());
    }

    @Test
    void testDeleteClientForProperty() {
        Property property = new Property("testProperty");
        when(propertyRepository.findByUserAccountName("testProperty")).thenReturn(Optional.of(property));
        Client client = new Client(12345678, "testClient", "email", "description", property);
        client.setId(12345678);
        when(clientRepository.findById(12345678)).thenReturn(Optional.of(client));
        when(clientRepository.findAllByPropertyId(property.getId())).thenReturn(List.of(client));
        ClientRequest cr = new ClientRequest(12345678, "testClient", "email", "description");
        clientService.addClientForProperty("testProperty", cr);
        verify(clientRepository).save(any(Client.class));
        assertEquals(1, clientRepository.findAllByPropertyId(property.getId()).size());
        clientService.deleteClientForProperty("testProperty", 12345678);
        verify(clientRepository).delete(client);
        when(clientRepository.findAllByPropertyId(property.getId())).thenReturn(List.of());
        assertEquals(0, clientRepository.findAllByPropertyId(property.getId()).size());
    }
}
