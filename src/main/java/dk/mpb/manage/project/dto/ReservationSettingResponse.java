package dk.mpb.manage.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.mpb.manage.project.entity.ReservationSetting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Reservation setting response DTO
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationSettingResponse {
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

    public ReservationSettingResponse(ReservationSetting reservationSetting){
        this.standardPrice = reservationSetting.getStandardPrice();
        this.JanuaryPrice = reservationSetting.getJanuaryPrice();
        this.FebruaryPrice = reservationSetting.getFebruaryPrice();
        this.MarchPrice = reservationSetting.getMarchPrice();
        this.AprilPrice = reservationSetting.getAprilPrice();
        this.MayPrice = reservationSetting.getMayPrice();
        this.JunePrice = reservationSetting.getJunePrice();
        this.JulyPrice = reservationSetting.getJulyPrice();
        this.AugustPrice = reservationSetting.getAugustPrice();
        this.SeptemberPrice = reservationSetting.getSeptemberPrice();
        this.OctoberPrice = reservationSetting.getOctoberPrice();
        this.NovemberPrice = reservationSetting.getNovemberPrice();
        this.DecemberPrice = reservationSetting.getDecemberPrice();
    }
}
