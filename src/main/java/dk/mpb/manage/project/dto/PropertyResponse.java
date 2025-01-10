package dk.mpb.manage.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.mpb.manage.project.entity.Property;
import lombok.Getter;
import lombok.Setter;

/**
 *  Property response DTO
 * */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertyResponse {
    private int id;
    private String name;
    private UserAccountResponse userAccount;

    public PropertyResponse(Property property) {
        this.id = property.getId();
        this.name = property.getName();
        if (property.getUserAccount() != null) {
            this.userAccount = new UserAccountResponse(property.getUserAccount());
        }
    }
}
