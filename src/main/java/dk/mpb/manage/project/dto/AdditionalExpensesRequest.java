package dk.mpb.manage.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *  Additional expenses request DTO
 * */
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdditionalExpensesRequest {
    private int id;
    private double amount;
    private String description;
    private int reservationId;
}
