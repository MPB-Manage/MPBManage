package dk.mpb.manage.project.entity;

import dk.mpb.manage.project.util.DateTimeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Additional expenses entity
 * */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class AdditionalExpenses extends DateTimeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    private String description;
    /**
     *  Reservation entity reference
     * */
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public AdditionalExpenses(double amount, String description, Reservation reservation) {
        this.amount = amount;
        this.description = description;
        this.reservation = reservation;
    }
}
