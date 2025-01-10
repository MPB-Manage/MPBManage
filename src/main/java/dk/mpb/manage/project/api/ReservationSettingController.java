package dk.mpb.manage.project.api;

import dk.mpb.manage.project.dto.ReservationSettingRequest;
import dk.mpb.manage.project.dto.ReservationSettingResponse;
import dk.mpb.manage.project.service.ReservationSettingService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/reservation_settings")
public class ReservationSettingController {
    private ReservationSettingService reservationSettingService;

    public ReservationSettingController(ReservationSettingService reservationSettingService) {
        this.reservationSettingService = reservationSettingService;
    }

    /**
     *  Get all reservation settings for a property
     * */
    @GetMapping
    public ReservationSettingResponse getAllReservationSettings(Principal principal) {
        return reservationSettingService.getAllReservationSettings(principal.getName());
    }
    /**
     *  Update all reservation settings for a property
     * */
    @PatchMapping
    public ReservationSettingResponse updateReservationSetting(Principal principal, @RequestBody ReservationSettingRequest reservationSettingRequest) {
        return reservationSettingService.updateReservationSetting(principal.getName(), reservationSettingRequest);
    }
}
