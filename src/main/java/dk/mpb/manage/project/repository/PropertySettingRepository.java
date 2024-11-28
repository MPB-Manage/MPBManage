package dk.mpb.manage.project.repository;

import dk.mpb.manage.project.entity.PropertySetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PropertySettingRepository extends JpaRepository<PropertySetting, Integer> {
    @Query(value = "SELECT * FROM property_setting WHERE property_id = :id", nativeQuery = true)
    Optional<PropertySetting> findByPropertyId(int id);
}