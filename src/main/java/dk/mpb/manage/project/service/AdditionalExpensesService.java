package dk.mpb.manage.project.service;

import dk.mpb.manage.project.dto.AdditionalExpensesRequest;
import dk.mpb.manage.project.entity.AdditionalExpenses;
import dk.mpb.manage.project.entity.Reservation;
import dk.mpb.manage.project.repository.AdditionalExpensesRepository;
import dk.mpb.manage.project.repository.ReservationRepository;
import org.springframework.stereotype.Service;

/**
 *  Additional expenses service
 * */
@Service
public class AdditionalExpensesService {

    AdditionalExpensesRepository additionalExpensesRepository;

    ReservationRepository reservationRepository;

    /**
     *  Constructor
     * */
    public AdditionalExpensesService(AdditionalExpensesRepository additionalExpensesRepository, ReservationRepository reservationRepository) {
        this.additionalExpensesRepository = additionalExpensesRepository;
        this.reservationRepository = reservationRepository;
    }

    /**
     *  Get all additional expenses
     * */
    public void getAllAdditionalExpenses(int reservationId) {
        additionalExpensesRepository.findAllByReservationId(reservationId);
    }

    /**
     *  Add additional expenses
     * */
    public void addAdditionalExpenses(AdditionalExpensesRequest additionalExpensesRequest) {
        Reservation reservation = reservationRepository.findById(additionalExpensesRequest.getReservationId()).get();
        AdditionalExpenses additionalExpenses = new AdditionalExpenses(additionalExpensesRequest.getAmount(), additionalExpensesRequest.getDescription(), reservation);
        additionalExpensesRepository.save(additionalExpenses);
    }

    /**
     *  Update additional expenses
     * */
    public void updateAdditionalExpenses(AdditionalExpensesRequest additionalExpensesRequest) {
        AdditionalExpenses additionalExpenses = additionalExpensesRepository.findById(additionalExpensesRequest.getId()).get();
        additionalExpenses.setAmount(additionalExpensesRequest.getAmount());
        additionalExpenses.setDescription(additionalExpensesRequest.getDescription());
        additionalExpensesRepository.save(additionalExpenses);
    }
}
