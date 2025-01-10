package dk.mpb.manage.security.dto;

import lombok.*;

/**
 *  User with roles request DTO
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRolesRequest {

    @NonNull
    String username;

    @NonNull
    String password;

    @NonNull
    String email;
}
