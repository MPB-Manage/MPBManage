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
@Entity
public class Client extends DateTimeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int telephoneNumber;
    private String name;
    private String email;
    private String description;
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    public Client(int telephoneNumber, String name, String email, String description, Property property) {
        this.telephoneNumber = telephoneNumber;
        this.name = name;
        this.email = email;
        this.description = description;
        this.property = property;
    }
}
