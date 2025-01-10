package dk.mpb.manage.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.mpb.manage.project.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *  Reservation response DTO
 * */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
    private int reservationId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private double price;
    private String clientName;
    private String clientPhone;
    private String clientEmail;
    private String clientDescription;
    private boolean isPaid;
    private boolean isConfirmed;
    private String billingStreet;
    private String billingCity;
    private String billingZip;
    private String billingCountry;

    public ReservationResponse(Reservation reservation){
        this.reservationId = reservation.getId();
        this.startDateTime = reservation.getStartDateTime();
        this.endDateTime = reservation.getEndDateTime();
        this.price = reservation.getPrice();
        this.clientName = reservation.getClientName();
        this.clientPhone = reservation.getClientPhone();
        this.clientEmail = reservation.getClientEmail();
        this.clientDescription = reservation.getClientDescription();
        this.isPaid = reservation.isPaid();
        this.isConfirmed = reservation.isConfirmed();
        this.billingStreet = reservation.getBillingStreet();
        this.billingCity = reservation.getBillingCity();
        this.billingZip = reservation.getBillingZip();
        this.billingCountry = reservation.getBillingCountry();
    }
}
