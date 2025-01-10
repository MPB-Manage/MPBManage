package dk.mpb.manage.project.api;

import dk.mpb.manage.project.dto.ClientRequest;
import dk.mpb.manage.project.dto.ClientResponse;
import dk.mpb.manage.project.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     *  Get all clients for a property
     * */
    @GetMapping
    public List<ClientResponse> getAllClientForProperty(Principal principal){
        return clientService.getAllClientForProperty(principal.getName());
    }

    /**
     * Add a client for a property
     * */
    @PostMapping
    public void addClientForProperty(Principal principal, @RequestBody ClientRequest clientRequest){
        clientService.addClientForProperty(principal.getName(), clientRequest);
    }

    /**
     *  Update a client for a property
     * */
    @DeleteMapping
    public void deleteClientForProperty(Principal principal, @PathVariable int id){
        clientService.deleteClientForProperty(principal.getName(), id);
    }
}
