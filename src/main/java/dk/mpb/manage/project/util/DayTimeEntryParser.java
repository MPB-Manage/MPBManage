package dk.mpb.manage.project.util;

import dk.mpb.manage.project.entity.Reservation;
import dk.mpb.manage.project.model.DayTimeEntry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DayTimeEntryParser {
    public List<DayTimeEntry> parseReservations(List<Reservation> reservations) {
        List<DayTimeEntry> dayTimeEntries = new ArrayList<>();

        for (Reservation reservation : reservations) {
            LocalDateTime start = reservation.getStartDateTime();
            LocalDateTime end = reservation.getEndDateTime();

            LocalDate currentDate = start.toLocalDate();
            LocalDate endDate = end.toLocalDate();

            dayTimeEntries.add(new DayTimeEntry(
                    currentDate,
                    start.toLocalTime(),
                    null,
                    reservation.getId()
            ));

            currentDate = currentDate.plusDays(1);
            while (!currentDate.isEqual(endDate)) {
                dayTimeEntries.add(new DayTimeEntry(
                        currentDate,
                        null,
                        null,
                        reservation.getId()
                ));
                currentDate = currentDate.plusDays(1);
            }
            dayTimeEntries.add(new DayTimeEntry(
                    endDate,
                    null,
                    end.toLocalTime(),
                    reservation.getId()
            ));
        }
        return dayTimeEntries;
    }
}
