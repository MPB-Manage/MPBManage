package dk.mpb.manage.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.mpb.manage.project.entity.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAccountRequest {
    private String username;
    private String password;

    public static UserAccount getUserAccountEntity(UserAccountRequest userAccountRequest) {
        return new UserAccount(
                userAccountRequest.username,
                userAccountRequest.password);
    }
}
