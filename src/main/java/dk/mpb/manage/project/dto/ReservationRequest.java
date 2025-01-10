package dk.mpb.manage.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *  Reservation request DTO
 * */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRequest {
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
}
