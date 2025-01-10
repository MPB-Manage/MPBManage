package dk.mpb.manage.project.service;

import dk.mpb.manage.project.dto.AdditionalExpensesRequest;
import dk.mpb.manage.project.entity.AdditionalExpenses;
import dk.mpb.manage.project.entity.Property;
import dk.mpb.manage.project.entity.Reservation;
import dk.mpb.manage.project.repository.AdditionalExpensesRepository;
import dk.mpb.manage.project.repository.PropertyRepository;
import dk.mpb.manage.project.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdditionalExpensesServiceTest {
    @Mock
    AdditionalExpensesRepository additionalExpensesRepository;
    @Mock
    ReservationRepository reservationRepository;
    @Mock
    PropertyRepository propertyRepository;

    AdditionalExpensesService additionalExpensesService;

    @BeforeEach
    void setUp() {
        additionalExpensesService = new AdditionalExpensesService(additionalExpensesRepository, reservationRepository);
        Reservation reservation = new Reservation(1, LocalDateTime.parse("2024-12-03T10:00:00"), LocalDateTime.parse("2024-12-04T10:00:00"), 1.00, "Test Client Name", "Test Phone", "Test Email", "Test Desc", true, true, "Test Street", "Test City", "Test Zip", "Test Country", new Property("Test Property"));
        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservation));
    }

    @Test
    void testGetAdditionalExpenses() {
        Reservation reservation = reservationRepository.findById(1).orElseThrow();
        AdditionalExpenses additionalExpense = new AdditionalExpenses(1.00, "Test Description", reservation);
        when(additionalExpensesRepository.findAllByReservationId(1)).thenReturn(List.of(additionalExpense));
        List<AdditionalExpenses> result = additionalExpensesRepository.findAllByReservationId(1);
        assertEquals(1, result.size());
        AdditionalExpenses retrievedExpense = result.get(0);
        assertEquals(1.00, retrievedExpense.getAmount());
        assertEquals("Test Description", retrievedExpense.getDescription());
        assertEquals(reservation, retrievedExpense.getReservation());
    }

    @Test
    void testCreateAdditionalExpenses() {
        Reservation reservation = reservationRepository.findById(1).orElseThrow();
        AdditionalExpenses additionalExpense = new AdditionalExpenses(1.00, "Test Description", reservation);
        when(additionalExpensesRepository.save(any(AdditionalExpenses.class))).thenReturn(additionalExpense);
        AdditionalExpenses result = additionalExpensesRepository.save(additionalExpense);
        assertEquals(1.00, result.getAmount());
        assertEquals("Test Description", result.getDescription());
        assertEquals(reservation, result.getReservation());
    }

    @Test
    void testUpdateAdditionalExpenses() {
        Reservation reservation = reservationRepository.findById(1).orElseThrow();
        AdditionalExpensesRequest additionalExpenseRequest = new AdditionalExpensesRequest(1, 1.00, "Test Description", reservation.getId());
        AdditionalExpenses additionalExpense = new AdditionalExpenses(1.00, "Test Description", reservation);
        when(additionalExpensesRepository.findById(1)).thenReturn(Optional.of(additionalExpense));
        when(additionalExpensesRepository.save(any(AdditionalExpenses.class))).thenReturn(additionalExpense);
        additionalExpensesService.updateAdditionalExpenses(additionalExpenseRequest);
        assertEquals(1.00, additionalExpense.getAmount());
        assertEquals("Test Description", additionalExpense.getDescription());
    }
}
