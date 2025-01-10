package dk.mpb.manage.project.service;

import dk.mpb.manage.project.dto.ClientRequest;
import dk.mpb.manage.project.dto.ClientResponse;
import dk.mpb.manage.project.entity.Client;
import dk.mpb.manage.project.entity.Property;
import dk.mpb.manage.project.repository.ClientRepository;
import dk.mpb.manage.project.repository.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 *  Client service
 * */
@Service
public class ClientService {
    private ClientRepository clientRepository;
    private PropertyRepository propertyRepository;

    public ClientService(ClientRepository clientRepository, PropertyRepository propertyRepository) {
        this.clientRepository = clientRepository;
        this.propertyRepository = propertyRepository;
    }

    /**
     *  Get all clients for property
     * */
    public List<ClientResponse> getAllClientForProperty(String name) {
        Property property = propertyRepository.findByUserAccountName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));
        List<Client> clients = clientRepository.findAllByPropertyId(property.getId());
        return clients.stream().map(client -> new ClientResponse(client)).toList();
    }

    /**
     *  Add client for property
     * */
    public void addClientForProperty(String name, ClientRequest clientRequest) {
        Property property = propertyRepository.findByUserAccountName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));
        Client client = new Client(clientRequest.getTelephoneNumber(), clientRequest.getName(), clientRequest.getEmail(), clientRequest.getDescription(), property);
        clientRepository.save(client);
    }

    /**
     *  Update client for property
     * */
    public void deleteClientForProperty(String name, int id) {
        Property property = propertyRepository.findByUserAccountName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
        if (client.getProperty().getId() != property.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Client does not belong to property");
        }
        clientRepository.delete(client);
    }
}
