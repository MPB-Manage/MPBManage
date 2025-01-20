package dk.mpb.manage.project.config;

import dk.mpb.manage.project.dto.ReservationSettingRequest;
import dk.mpb.manage.project.entity.*;
import dk.mpb.manage.project.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;

import static dk.mpb.manage.security.entity.Role.USER;

/**
 *  Developer data for testing
 * */
@Configuration
public class DeveloperData implements ApplicationRunner {
    UserAccountRepository userAccountRepository;
    PropertyRepository propertyRepository;
    ReservationRepository reservationRepository;
    ClientRepository clientRepository;
    ReservationSettingRepository reservationSettingRepository;
    PropertySettingRepository propertySettingRepository;
    AdditionalExpensesRepository additionalExpensesRepository;

    public DeveloperData(UserAccountRepository userAccountRepository, PropertyRepository propertyRepository, ReservationRepository reservationRepository, ClientRepository clientRepository, ReservationSettingRepository reservationSettingRepository, PropertySettingRepository propertySettingRepository, AdditionalExpensesRepository additionalExpensesRepository) {
        this.userAccountRepository = userAccountRepository;
        this.propertyRepository = propertyRepository;
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
        this.reservationSettingRepository = reservationSettingRepository;
        this.propertySettingRepository = propertySettingRepository;
        this.additionalExpensesRepository = additionalExpensesRepository;
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

        // Create reservations
        Reservation reservation1 = new Reservation(1, LocalDateTime.parse("2024-12-03T10:00:00"), LocalDateTime.parse("2024-12-04T10:00:00"), 1.00, "Test Client Name", "Test Phone", "Test Email", "Test Desc", true, true, "Test Street", "Test City", "Test Zip", "Test Country", property);
        Reservation reservation2 = new Reservation(2, LocalDateTime.parse("2024-12-05T10:00:00"), LocalDateTime.parse("2024-12-06T10:00:00"), 2.00, "Test Client Name", "Test Phone", "Test Email", "Test Desc", false, false, "Test Street", "Test City", "Test Zip", "Test Country", property);
        Reservation reservation3 = new Reservation(3, LocalDateTime.parse("2024-12-07T10:00:00"), LocalDateTime.parse("2024-12-08T10:00:00"), 3.00, "Test Client Name", "Test Phone", "Test Email", "Test Desc", false, true, "Test Street", "Test City", "Test Zip", "Test Country", property);
        Reservation reservation4 = new Reservation(4, LocalDateTime.parse("2025-01-01T10:00:00"), LocalDateTime.parse("2025-01-02T10:00:00"), 2.00, "Test Client Name", "Test Phone", "Test Email", "Test Desc", false, false, "Test Street", "Test City", "Test Zip", "Test Country", property);
        Reservation reservation5 = new Reservation(5, LocalDateTime.parse("2025-01-07T06:00:00"), LocalDateTime.parse("2025-01-08T10:00:00"), 3.00, "Test Client Name", "Test Phone", "Test Email", "Test Desc", true, true, "Test Street", "Test City", "Test Zip", "Test Country", property);
        Reservation reservation6 = new Reservation(6, LocalDateTime.parse("2025-01-10T15:00:00"), LocalDateTime.parse("2025-01-12T10:00:00"), 2.00, "Test Client Name", "Test Phone", "Test Email", "Test Desc", false, false, "Test Street", "Test City", "Test Zip", "Test Country", property);
        Reservation reservation7 = new Reservation(7, LocalDateTime.parse("2025-02-16T16:00:00"), LocalDateTime.parse("2025-02-19T10:00:00"), 3.00, "Test Client Name", "Test Phone", "Test Email", "Test Desc", false, true, "Test Street", "Test City", "Test Zip", "Test Country", property);
        Reservation reservation8 = new Reservation(8, LocalDateTime.parse("2025-02-21T12:00:00"), LocalDateTime.parse("2025-02-22T10:00:00"), 2.00, "Test Client Name", "Test Phone", "Test Email", "Test Desc", false, false, "Test Street", "Test City", "Test Zip", "Test Country", property);
        Reservation reservation9 = new Reservation(9, LocalDateTime.parse("2025-02-26T11:00:00"), LocalDateTime.parse("2025-02-27T10:00:00"), 3.00, "Test Client Name", "Test Phone", "Test Email", "Test Desc", true, true, "Test Street", "Test City", "Test Zip", "Test Country", property);
        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);
        reservationRepository.save(reservation3);
        reservationRepository.save(reservation4);
        reservationRepository.save(reservation5);
        reservationRepository.save(reservation6);
        reservationRepository.save(reservation7);
        reservationRepository.save(reservation8);
        reservationRepository.save(reservation9);

        // Create a test clients
        Client client1 = new Client(12345678, "Test Name1", "TestEmail1@email.dk", "Test Desc for the first client", property);
        Client client2 = new Client(12345679, "Test Name2", "Testemail2@email.dk", "Test Desc for the second client", property);
        Client client3 = new Client(12345680, "Test Name3", "Testemail3@email.dk", "Test Desc for the third client", property);
        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);

        // Create a test additional expenses
        AdditionalExpenses additionalExpenses1 = new AdditionalExpenses(100.00, "Test description for additional expenses", reservation1);
        AdditionalExpenses additionalExpenses2 = new AdditionalExpenses(200.00, "Test description for additional expenses", reservation1);
        additionalExpensesRepository.save(additionalExpenses1);
        additionalExpensesRepository.save(additionalExpenses2);
    }
}