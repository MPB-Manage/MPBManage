package dk.mpb.manage.project.config;

import dk.mpb.manage.project.entity.Property;
import dk.mpb.manage.project.entity.UserAccount;
import dk.mpb.manage.project.repository.PropertyRepository;
import dk.mpb.manage.project.repository.UserAccountRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import static dk.mpb.manage.security.entity.Role.USER;

@Configuration
public class DeveloperData implements ApplicationRunner {
    UserAccountRepository userAccountRepository;
    PropertyRepository propertyRepository;

    public DeveloperData(UserAccountRepository userAccountRepository, PropertyRepository propertyRepository) {
        this.userAccountRepository = userAccountRepository;
        this. propertyRepository = propertyRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create a test property
        Property property = new Property("Test Property");

        // Create a test user
        UserAccount userAccount = new UserAccount("user@user.dk", "1234");
        userAccount.addRole(USER);
        userAccountRepository.save(userAccount);

        // Add the user to the property
        property.setUserAccount(userAccount);
        propertyRepository.save(property);
    }
}