package dk.mpb.manage.project.api;

import dk.mpb.manage.project.dto.ReservationRequest;
import dk.mpb.manage.project.dto.ReservationResponse;
import dk.mpb.manage.project.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // USER - get all reservations
    @GetMapping()
    public List<ReservationResponse> getAllReservations(Principal principal) {
        return reservationService.getAllReservations(principal.getName());
    }

    // USER - get all reservations for the current year
    @GetMapping("/{year}")
    public List<ReservationResponse> getAllReservationsByYear(Principal principal, @PathVariable int year) {
        return reservationService.getAllReservationsByYear(principal.getName(), year);
    }

    // User - create reservation
    @PostMapping()
    public void createReservation(Principal principal, @RequestBody ReservationRequest reservationRequest) {
        reservationService.createReservation(principal.getName(), reservationRequest);
    }

    // USER - delete reservation
    @DeleteMapping("/{id}")
    public void deleteReservation(Principal principal, @PathVariable int id) {
        reservationService.deleteReservation(principal.getName(), id);
    }
}
