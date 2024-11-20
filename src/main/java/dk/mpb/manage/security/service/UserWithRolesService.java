package dk.mpb.manage.security.service;

import dk.mpb.manage.security.dto.UserWithRolesRequest;
import dk.mpb.manage.security.dto.UserWithRolesResponse;
import dk.mpb.manage.security.entity.Role;
import dk.mpb.manage.security.entity.UserWithRoles;
import dk.mpb.manage.security.repository.UserAccountSecurityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserWithRolesService {

  private  final UserAccountSecurityRepository userWithRolesRepository;

  public UserWithRolesService(UserAccountSecurityRepository userWithRolesRepository) {
    this.userWithRolesRepository = userWithRolesRepository;
  }

  public UserWithRolesResponse addUserWithRoles(UserWithRolesRequest body, Role role){
    if(userWithRolesRepository.existsById(body.getUsername())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This user name is taken");
    }
    String pw = body.getPassword();
    UserWithRoles userWithRoles = new UserWithRoles(body.getUsername(), pw);
    if(role !=null  ) {
      userWithRoles.addRole(role);
    }
    return new UserWithRolesResponse(userWithRolesRepository.save(userWithRoles));
  }
}
