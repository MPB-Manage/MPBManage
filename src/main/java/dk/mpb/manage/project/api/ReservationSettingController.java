package dk.mpb.manage.project.api;

import dk.mpb.manage.project.dto.ReservationSettingRequest;
import dk.mpb.manage.project.dto.ReservationSettingResponse;
import dk.mpb.manage.project.service.ReservationSettingService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/reservation_setting")
public class ReservationSettingController {
    private ReservationSettingService reservationSettingService;

    public ReservationSettingController(ReservationSettingService reservationSettingService) {
        this.reservationSettingService = reservationSettingService;
    }

    //User - update reservation setting
    @PatchMapping
    public ReservationSettingResponse updateReservationSetting(Principal principal, @RequestBody ReservationSettingRequest reservationSettingRequest) {
        return reservationSettingService.updateReservationSetting(principal.getName(), reservationSettingRequest);
    }
}
