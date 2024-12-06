package dk.mpb.manage.project.service;

import dk.mpb.manage.project.dto.ReservationRequest;
import dk.mpb.manage.project.dto.ReservationResponse;
import dk.mpb.manage.project.entity.Property;
import dk.mpb.manage.project.entity.Reservation;
import dk.mpb.manage.project.repository.PropertyRepository;
import dk.mpb.manage.project.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    private PropertyRepository propertyRepository;

    public ReservationService(ReservationRepository reservationRepository, PropertyRepository propertyRepository) {
        this.reservationRepository = reservationRepository;
        this.propertyRepository = propertyRepository;
    }
    public List<ReservationResponse> getAllReservationsByYear(String name, int year) {
        Property property = propertyRepository.findByUserAccountName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));
        List<Reservation> reservations = reservationRepository.findAllByPropertyIdAndYear(property.getId(), year);
        List<ReservationResponse> reservationResponses = reservations.stream().map(res -> new ReservationResponse(res)).toList();
        return reservationResponses;
    }

    public void createReservation(String name, ReservationRequest reservationRequest) {
        Property property = propertyRepository.findByUserAccountName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));
        Reservation reservation = new Reservation(reservationRequest.getStartDateTime(), reservationRequest.getEndDateTime(), reservationRequest.getPrice(), reservationRequest.getClientName(), reservationRequest.getClientPhone(), reservationRequest.getClientEmail(), reservationRequest.getClientDescription(), reservationRequest.isPaid(), reservationRequest.isConfirmed(), reservationRequest.getBillingStreet(), reservationRequest.getBillingCity(), reservationRequest.getBillingZip(), reservationRequest.getBillingCountry(), property);
        reservationRepository.save(reservation);
    }

    public void deleteReservation(String name, int id) {
        Property property = propertyRepository.findByUserAccountName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
        if (reservation.getProperty().getId() != property.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Reservation does not belong to property");
        }
        reservationRepository.delete(reservation);
    }
}
