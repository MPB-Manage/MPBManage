package dk.mpb.manage.project.repository;

import dk.mpb.manage.project.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 *  Property repository
 * */
public interface PropertyRepository extends JpaRepository<Property, Integer> {
    /**
     *  Find property by user account name
     * */
    @Query(value = "SELECT * FROM property WHERE user_account_id = :name", nativeQuery = true)
    Optional<Property> findByUserAccountName(String name);
}
