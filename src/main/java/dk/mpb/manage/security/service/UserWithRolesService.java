package dk.mpb.manage.security.service;

import dk.mpb.manage.security.dto.UserWithRolesRequest;
import dk.mpb.manage.security.dto.UserWithRolesResponse;
import dk.mpb.manage.security.entity.Role;
import dk.mpb.manage.security.entity.UserWithRoles;
import dk.mpb.manage.security.repository.UserWithRolesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserWithRolesService {

  private  final UserWithRolesRepository userWithRolesRepository;

  public UserWithRolesService(UserWithRolesRepository userWithRolesRepository) {
    this.userWithRolesRepository = userWithRolesRepository;
  }

  public UserWithRolesResponse getUserWithRoles(String id){
    UserWithRoles user = userWithRolesRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"UserAccount not found"));
    return new UserWithRolesResponse(user);
  }

  //Make sure that this can ONLY be called by an admin
  public UserWithRolesResponse addRole(String username , Role role){
    UserWithRoles user = userWithRolesRepository.findById(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"UserAccount not found"));
    user.addRole(role);
    return new UserWithRolesResponse(userWithRolesRepository.save(user));
  }

  //Make sure that this can ONLY be called by an admin
  public UserWithRolesResponse removeRole(String username , Role role){

    UserWithRoles user = userWithRolesRepository.findById(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"UserAccount not found"));
    user.removeRole(role);
    return new UserWithRolesResponse(userWithRolesRepository.save(user));
  }

  public UserWithRolesResponse editUserWithRoles(String username , UserWithRolesRequest body){
    UserWithRoles user = userWithRolesRepository.findById(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"UserAccount not found"));
    user.setEmail(body.getEmail());
    user.setPassword(body.getPassword());
    return new UserWithRolesResponse(userWithRolesRepository.save(user));
  }

  public UserWithRolesResponse addUserWithRoles(UserWithRolesRequest body, Role role){
    if(userWithRolesRepository.existsById(body.getUsername())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This user name is taken");
    }
    if(userWithRolesRepository.existsByEmail(body.getEmail())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This email is used by another user");
    }
    String pw = body.getPassword();
    UserWithRoles userWithRoles = new UserWithRoles(body.getUsername(), pw, body.getEmail());
    if(role !=null  ) {
      userWithRoles.addRole(role);
    }
    return new UserWithRolesResponse(userWithRolesRepository.save(userWithRoles));
  }

}
