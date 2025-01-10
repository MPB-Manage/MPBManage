package dk.mpb.manage.project.api;

import dk.mpb.manage.project.dto.PropertySettingRequest;
import dk.mpb.manage.project.dto.PropertySettingResponse;
import dk.mpb.manage.project.service.PropertyService;
import dk.mpb.manage.project.service.PropertySettingService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/property_settings")
public class PropertySettingController {
    private PropertySettingService propertySettingService;

    public PropertySettingController(PropertySettingService propertySettingService) {
        this.propertySettingService = propertySettingService;
    }

    /**
     *  Get all property settings for a property
     * */
    @GetMapping
    public PropertySettingResponse getAllPropertySettings(Principal principal) {
        return propertySettingService.getAllPropertySettings(principal.getName());
    }

    /**
     *  Update all property settings for a property
     * */
    @PatchMapping
    public PropertySettingResponse updatePropertySettingById(Principal principal, @RequestBody PropertySettingRequest propertySettingRequest) {
        return propertySettingService.updatePropertySetting(principal.getName(), propertySettingRequest);
    }
}
