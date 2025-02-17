package dk.mpb.manage.security.dto;

import dk.mpb.manage.security.entity.UserWithRoles;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  User with roles response DTO
 * */
@Getter
@Setter
public class UserWithRolesResponse {

    String userName;

    List<String> roleNames;

    String email;

    public UserWithRolesResponse(UserWithRoles userWithRoles) {
        this.userName = userWithRoles.getUsername();
        this.roleNames = userWithRoles.getRoles().stream().map(role -> role.toString()).collect(Collectors.toList());
    }
}
