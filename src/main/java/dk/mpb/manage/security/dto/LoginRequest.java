package dk.mpb.manage.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Login request DTO
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

   private String username;

   private String password;
}
