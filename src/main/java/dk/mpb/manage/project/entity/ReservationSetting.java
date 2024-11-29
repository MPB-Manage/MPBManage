package dk.mpb.manage.project.entity;

import dk.mpb.manage.project.util.DateTimeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReservationSetting extends DateTimeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
    @OneToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private Property property;

    public ReservationSetting(Property property) {
        this.property = property;
    }
}
