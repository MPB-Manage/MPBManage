package dk.mpb.manage.project.config;

import dk.mpb.manage.project.entity.UserAccount;
import dk.mpb.manage.project.repository.UserAccountRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import static dk.mpb.manage.security.entity.Role.ADMIN;

@Configuration
public class DeveloperData implements ApplicationRunner {
    UserAccountRepository userAccountRepository;

    public DeveloperData(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserAccount admin = new UserAccount("admin", "password123", "admin@admin");
        admin.addRole(ADMIN);
        userAccountRepository.save(admin);
    }
}