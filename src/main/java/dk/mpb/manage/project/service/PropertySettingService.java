package dk.mpb.manage.project.service;

import dk.mpb.manage.project.dto.PropertySettingRequest;
import dk.mpb.manage.project.dto.PropertySettingResponse;
import dk.mpb.manage.project.entity.Property;
import dk.mpb.manage.project.entity.PropertySetting;
import dk.mpb.manage.project.repository.PropertyRepository;
import dk.mpb.manage.project.repository.PropertySettingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 *  Property setting service
 * */
@Service
public class PropertySettingService {
    private PropertyRepository propertyRepository;
    private PropertySettingRepository propertySettingRepository;

    public PropertySettingService(PropertyRepository propertyRepository, PropertySettingRepository propertySettingRepository) {
        this.propertyRepository = propertyRepository;
        this.propertySettingRepository = propertySettingRepository;
    }

    /**
     *  Get all property settings
     * */
    public PropertySettingResponse updatePropertySetting(String name, PropertySettingRequest propertySettingRequest) {
        Property property = propertyRepository.findByUserAccountName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found"));
        PropertySetting propertySetting = propertySettingRepository.findByPropertyId(property.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Property setting not found"));
        if(propertySettingRequest.getStreet() != null) {
            propertySetting.setStreet(propertySettingRequest.getStreet());
        } else if(propertySettingRequest.getCity() != null) {
            propertySetting.setCity(propertySettingRequest.getCity());
        } else if(propertySettingRequest.getZipCode() != null) {
            propertySetting.setZipCode(propertySettingRequest.getZipCode());
        } else if(propertySettingRequest.getCountry() != null) {
            propertySetting.setCountry(propertySettingRequest.getCountry());
        } else if(propertySettingRequest.getPhone() != null) {
            propertySetting.setPhone(propertySettingRequest.getPhone());
        } else if(propertySettingRequest.getEmail() != null) {
            propertySetting.setEmail(propertySettingRequest.getEmail());
        }
        PropertySetting propertySettingResponse = propertySettingRepository.save(propertySetting);
        return new PropertySettingResponse(propertySettingResponse);
    }
}
