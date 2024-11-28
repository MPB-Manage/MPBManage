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
public class Property extends DateTimeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    public Property(String name) {
        this.name = name;
    }
}
