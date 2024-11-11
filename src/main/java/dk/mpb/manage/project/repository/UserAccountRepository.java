package dk.mpb.manage.project.repository;

import dk.mpb.manage.project.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    boolean existsByEmail(String email);
}
