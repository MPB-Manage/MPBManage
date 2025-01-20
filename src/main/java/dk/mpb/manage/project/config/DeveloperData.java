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
    }
}
