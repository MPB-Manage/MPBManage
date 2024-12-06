package dk.mpb.manage.project.api;

import dk.mpb.manage.project.dto.PropertyRequest;
import dk.mpb.manage.project.dto.PropertyResponse;
import dk.mpb.manage.project.entity.Property;
import dk.mpb.manage.project.service.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    //ADMIN - get all properties
    @GetMapping
    public List<PropertyResponse> getAllProperties() {
        return propertyService.getProperty();
    }

    //ADMIN - create a property
    @PostMapping
    public void createProperty(@RequestBody PropertyRequest propertyRequest) {
        propertyService.createProperty(propertyRequest);
    }
}
