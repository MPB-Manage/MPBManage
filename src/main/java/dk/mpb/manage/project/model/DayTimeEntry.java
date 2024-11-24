package dk.mpb.manage.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


public record DayTimeEntry(
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        Integer Id
) {}