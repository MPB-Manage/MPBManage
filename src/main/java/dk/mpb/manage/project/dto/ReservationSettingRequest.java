package dk.mpb.manage.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Reservation setting request DTO
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationSettingRequest {
    private double standardPrice;
    private double JanuaryPrice;
    private double FebruaryPrice;
    private double MarchPrice;
    private double AprilPrice;
    private double MayPrice;
    private double JunePrice;
    private double JulyPrice;
    private double AugustPrice;
    private double SeptemberPrice;
    private double OctoberPrice;
    private double NovemberPrice;
    private double DecemberPrice;
}
