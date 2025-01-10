package dk.mpb.manage.project.repository;

import dk.mpb.manage.project.entity.AdditionalExpenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdditionalExpensesRepository extends JpaRepository<AdditionalExpenses, Integer> {
    List<AdditionalExpenses> findAllByReservationId(int reservationId);
}
