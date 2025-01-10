package dk.mpb.manage.project.repository;

import dk.mpb.manage.project.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query(value = "SELECT * FROM reservations WHERE property_id = :id AND YEAR(start_date) = :year", nativeQuery = true)
    List<Reservation> findAllByPropertyIdAndYear(int id, int year);

    List<Reservation> findAllByPropertyId(int id);
}
