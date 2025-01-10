package dk.mpb.manage.project.service;

import dk.mpb.manage.project.dto.PropertyRequest;
import dk.mpb.manage.project.dto.PropertyResponse;
import dk.mpb.manage.project.entity.Property;
import dk.mpb.manage.project.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 *  Property service
 * */
@Service
public class PropertyService {

    PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    /**
     *  Get all properties
     * */
    public List<PropertyResponse> getProperty() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream().map(property -> new PropertyResponse(property)).toList();
    }

    /**
     *  Create property
     * */
    public void createProperty(PropertyRequest propertyRequest) {
        Property property = new Property(propertyRequest.getName());
        propertyRepository.save(property);
    }

}
