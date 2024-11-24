package dk.mpb.manage.project.repository;

import dk.mpb.manage.project.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
