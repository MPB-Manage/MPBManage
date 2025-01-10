package dk.mpb.manage.security.api;

import dk.mpb.manage.security.entity.Role;
import dk.mpb.manage.security.dto.UserWithRolesRequest;
import dk.mpb.manage.security.dto.UserWithRolesResponse;
import dk.mpb.manage.security.service.UserWithRolesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 *  User with role controller
 * */
@RestController
@RequestMapping("/api/user-with-role")
public class UserWithRoleController {

  static Role DEFAULT_ROLE_TO_ASSIGN = Role.USER;

  UserWithRolesService userWithRolesService;

  public UserWithRoleController(UserWithRolesService userWithRolesService) {
    this.userWithRolesService = userWithRolesService;
  }

  /**
   *  Add user with roles
   * */
  @PostMapping
  public UserWithRolesResponse addUserWithRoles(@RequestBody UserWithRolesRequest request) {
    return userWithRolesService.addUserWithRoles (request, DEFAULT_ROLE_TO_ASSIGN);
  }
}
