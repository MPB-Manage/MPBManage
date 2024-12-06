package dk.mpb.manage.project.config;

import dk.mpb.manage.project.entity.Property;
import dk.mpb.manage.project.entity.Reservation;
import dk.mpb.manage.project.entity.UserAccount;
import dk.mpb.manage.project.repository.PropertyRepository;
import dk.mpb.manage.project.repository.ReservationRepository;
import dk.mpb.manage.project.repository.UserAccountRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

import static dk.mpb.manage.security.entity.Role.USER;

@Configuration
public class DeveloperData implements ApplicationRunner {
    UserAccountRepository userAccountRepository;
    PropertyRepository propertyRepository;
    ReservationRepository reservationRepository;

    public DeveloperData(UserAccountRepository userAccountRepository, PropertyRepository propertyRepository, ReservationRepository reservationRepository) {
        this.userAccountRepository = userAccountRepository;
        this.propertyRepository = propertyRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create a test property
        Property property = new Property("Test Property");

        // Create a test user
        UserAccount userAccount = new UserAccount("user@user.dk", "1234");
        userAccount.addRole(USER);
        userAccountRepository.save(userAccount);

        property.setUserAccount(userAccount);
        propertyRepository.save(property);

        // Create reservation
        Reservation reservation = new Reservation(1, LocalDateTime.parse("2024-12-03T10:00:00"), LocalDateTime.parse("2024-12-04T10:00:00"), 1.00, "Test Client Name", "Test Phone", "Test Email", "Test Desc", true, true, "Test Street", "Test City", "Test Zip", "Test Country", property);
        reservationRepository.save(reservation);
    }
}