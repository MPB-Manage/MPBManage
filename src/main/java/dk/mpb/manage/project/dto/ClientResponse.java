package dk.mpb.manage.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.mpb.manage.project.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Client response DTO
 * */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponse {
    private int id;
    private int telephoneNumber;
    private String name;
    private String email;
    private String description;

    public ClientResponse(Client client){
        this.id = client.getId();
        this.telephoneNumber = client.getTelephoneNumber();
        this.name = client.getName();
        this.email = client.getEmail();
        this.description = client.getDescription();
    }
}
