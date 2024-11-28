package dk.mpb.manage.project.repository;

import dk.mpb.manage.project.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
    @Query(value = "SELECT * FROM property WHERE user_account_name = :name", nativeQuery = true)
    Optional<Property> findByUserAccountName(String name);
}
