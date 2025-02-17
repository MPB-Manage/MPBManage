package dk.mpb.manage.security.repository;

import dk.mpb.manage.security.entity.UserWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  User account security repository
 * */
public interface
UserAccountSecurityRepository extends JpaRepository<UserWithRoles,String> {
}
