package dk.mpb.manage.project.entity;

import dk.mpb.manage.project.util.DateTimeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 *  Property entity
 * */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Property extends DateTimeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    /**
     *  User account entity reference
     * */
    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;
    /**
     *  Property setting entity reference
     * */
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, optional = true)
    private PropertySetting propertySetting;
    /**
     *  Reservation setting entity reference
     * */
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, optional = true)
    private ReservationSetting reservationSetting;
    /**
     *  Reservation entity reference
     * */
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
    /**
     *  Client entity reference
     * */
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Client> clients = new ArrayList<>();
    public Property(String name) {
        this.name = name;
        this.propertySetting = new PropertySetting(this);
        this.reservationSetting = new ReservationSetting(this);
    }
}
