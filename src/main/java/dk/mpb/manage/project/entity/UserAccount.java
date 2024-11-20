package dk.mpb.manage.project.entity;

import dk.mpb.manage.security.entity.UserWithRoles;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class UserAccount extends UserWithRoles {
    public UserAccount(String username, String password) {
        super(username, password);
    }
}
