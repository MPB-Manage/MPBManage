package dk.mpb.manage.project.entity;

import dk.mpb.manage.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  User account entity
 * */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class UserAccount extends UserWithRoles {
    /**
     *  Property entity reference
     * */
    @OneToOne(mappedBy = "userAccount")
    private Property property;
    /**
     *  Add property to user account
     * */
    public void addProperty(Property property) {
        this.property = property;
        property.setUserAccount(this);
    }
    public UserAccount(String username, String password) {
        super(username, password);
    }
}
