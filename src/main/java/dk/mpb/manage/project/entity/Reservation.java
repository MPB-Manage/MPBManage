package dk.mpb.manage.project.entity;

import dk.mpb.manage.project.dto.ReservationRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *  Reservation entity
 * */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
    /**
     *  Property entity reference
     * */
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    public Reservation(LocalDateTime startDateTime, LocalDateTime endDateTime, double price, String clientName, String clientPhone, String clientEmail, String clientDescription, boolean isPaid, boolean isConfirmed, String billingStreet, String billingCity, String billingZip, String billingCountry, Property property) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.price = price;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientEmail = clientEmail;
        this.clientDescription = clientDescription;
        this.isPaid = isPaid;
        this.isConfirmed = isConfirmed;
        this.billingStreet = billingStreet;
        this.billingCity = billingCity;
        this.billingZip = billingZip;
        this.billingCountry = billingCountry;
        this.property = property;
    }
}
