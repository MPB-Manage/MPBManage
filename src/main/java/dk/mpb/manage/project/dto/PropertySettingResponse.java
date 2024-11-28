package dk.mpb.manage.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dk.mpb.manage.project.entity.PropertySetting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertySettingResponse {
    private String street;
    private String city;
    private String zipCode;
    private String country;
    private String phone;
    private String email;

    public PropertySettingResponse(PropertySetting propertySetting) {
        this.street = propertySetting.getStreet();
        this.city = propertySetting.getCity();
        this.zipCode = propertySetting.getZipCode();
        this.country = propertySetting.getCountry();
        this.phone = propertySetting.getPhone();
        this.email = propertySetting.getEmail();
    }
}
