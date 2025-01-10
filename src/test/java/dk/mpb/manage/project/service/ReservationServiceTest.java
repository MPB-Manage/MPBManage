package dk.mpb.manage.project.service;

import dk.mpb.manage.project.dto.ReservationRequest;
import dk.mpb.manage.project.dto.ReservationResponse;
import dk.mpb.manage.project.entity.Property;
import dk.mpb.manage.project.entity.Reservation;
import dk.mpb.manage.project.repository.PropertyRepository;
import dk.mpb.manage.project.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    PropertyRepository propertyRepository;

    @InjectMocks
    ReservationService reservationService;

    Property testProperty;
    Reservation testReservation;

    @BeforeEach
    void setUp() {
        testProperty = new Property();
        testProperty.setId(1);
        testProperty.setName("Test Property");

        testReservation = new Reservation(
                LocalDateTime.of(2024, 1, 15, 14, 0),
                LocalDateTime.of(2024, 1, 20, 11, 0),
                250.0,
                "John Doe",
                "1234567890",
                "john.doe@example.com",
                "Vacation stay",
                false,
                true,
                "123 Test St",
                "Test City",
                "12345",
                "Testland",
                testProperty
        );
    }

    @Test
    void testGetAllReservationsByYear() {
        when(propertyRepository.findByUserAccountName("testUser"))
                .thenReturn(Optional.of(testProperty));

        when(reservationRepository.findAllByPropertyIdAndYear(1, 2024))
                .thenReturn(List.of(testReservation));

        List<ReservationResponse> responses = reservationService.getAllReservationsByYear("testUser", 2024);

        assertNotNull(responses);
        assertEquals(1, responses.size());
        verify(propertyRepository).findByUserAccountName("testUser");
        verify(reservationRepository).findAllByPropertyIdAndYear(1, 2024);
    }

    @Test
    void testCreateReservation() {
        when(propertyRepository.findByUserAccountName("testUser"))
                .thenReturn(Optional.of(testProperty));

        ReservationRequest request = new ReservationRequest(
                LocalDateTime.of(2024, 1, 15, 14, 0),
                LocalDateTime.of(2024, 1, 20, 11, 0),
                250.0,
                "John Doe",
                "1234567890",
                "john.doe@example.com",
                "Vacation stay",
                false,
                true,
                "123 Test St",
                "Test City",
                "12345",
                "Testland"
        );

        reservationService.createReservation("testUser", request);

        verify(propertyRepository).findByUserAccountName("testUser");
        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    void testDeleteReservation() {
        when(propertyRepository.findByUserAccountName("testUser"))
                .thenReturn(Optional.of(testProperty));

        when(reservationRepository.findById(1))
                .thenReturn(Optional.of(testReservation));

        reservationService.deleteReservation("testUser", 1);

        verify(propertyRepository).findByUserAccountName("testUser");
        verify(reservationRepository).findById(1);
        verify(reservationRepository).delete(testReservation);
    }

    @Test
    void testDeleteReservationThrowsForbidden() {
        Property anotherProperty = new Property();
        anotherProperty.setId(2);
        testReservation.setProperty(anotherProperty);

        when(propertyRepository.findByUserAccountName("testUser"))
                .thenReturn(Optional.of(testProperty));

        when(reservationRepository.findById(1))
                .thenReturn(Optional.of(testReservation));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                reservationService.deleteReservation("testUser", 1));

        assertEquals("403 FORBIDDEN \"Reservation does not belong to property\"", exception.getMessage());
        verify(propertyRepository).findByUserAccountName("testUser");
        verify(reservationRepository).findById(1);
        verify(reservationRepository, never()).delete(any(Reservation.class));
    }
}
