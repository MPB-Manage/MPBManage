package dk.mpb.manage.project.repository;

import dk.mpb.manage.project.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  User account repository
 * */
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}
