package dk.mpb.manage.security.service;

import dk.mpb.manage.security.entity.UserWithRoles;
import dk.mpb.manage.security.repository.UserAccountSecurityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
  public static final String WRONG_USERNAME_OR_PASSWORD = "Incorrect username or password";
  UserAccountSecurityRepository userWithRolesRepository;

  public UserDetailsServiceImp(UserAccountSecurityRepository userWithRolesRepository) {
    this.userWithRolesRepository = userWithRolesRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final Optional<UserWithRoles> optionalUser = userWithRolesRepository.findById(username);
    return optionalUser.orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,WRONG_USERNAME_OR_PASSWORD));
  }
}
