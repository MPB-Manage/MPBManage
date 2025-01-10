package dk.mpb.manage.project.entity;

import dk.mpb.manage.project.util.DateTimeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Property setting entity
 * */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PropertySetting extends DateTimeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String street;
    private String city;
    private String zipCode;
    private String country;
    private String phone;
    private String email;
    /**
     *  Property entity reference
     * */
    @OneToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private Property property;

    public PropertySetting(Property property) {
        this.property = property;
    }
}
