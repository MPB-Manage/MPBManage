package dk.mpb.manage.project.repository;

import dk.mpb.manage.project.entity.ReservationSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReservationSettingRepository extends JpaRepository<ReservationSetting, Integer> {
    @Query(value = "SELECT * FROM reservation_setting WHERE property_id = :id", nativeQuery = true)
    Optional<ReservationSetting> findByPropertyId(int id);
}
