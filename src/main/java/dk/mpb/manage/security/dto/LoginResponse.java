package dk.mpb.manage.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 *  Login response DTO
 * */
@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {

  private String username;

  private String token;

  private List<String> roles;

  public LoginResponse(String userName, String token, List<String> roles) {
    this.username = userName;
    this.token = token;
    this.roles = roles;
  }
}
