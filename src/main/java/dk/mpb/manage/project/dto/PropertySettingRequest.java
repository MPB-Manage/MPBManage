package dk.mpb.manage.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Property setting request DTO
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertySettingRequest {
    private String street;
    private String city;
    private String zipCode;
    private String country;
    private String phone;
    private String email;
}
