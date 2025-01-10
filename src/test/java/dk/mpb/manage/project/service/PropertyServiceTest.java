package dk.mpb.manage.project.service;

import dk.mpb.manage.project.dto.PropertyRequest;
import dk.mpb.manage.project.dto.PropertyResponse;
import dk.mpb.manage.project.entity.Property;
import dk.mpb.manage.project.repository.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {
    @Mock
    PropertyRepository propertyRepository;

    PropertyService propertyService;

    @BeforeEach
    void setUp() {
        propertyService = new PropertyService(propertyRepository);
    }

    Property testPropertySetup() {
        return new Property("testProperty");
    }

    @Test
    void testGetProperty() {
        Property testProperty = testPropertySetup();
        when(propertyRepository.findAll()).thenReturn(List.of(testProperty));
        List<PropertyResponse> properties = propertyService.getProperty();
        assertEquals(1, properties.size());
        assertEquals(testProperty.getName(), properties.get(0).getName());
    }

    @Test
    void testCreateProperty() {
        Property testProperty = testPropertySetup();
        propertyService.createProperty(new PropertyRequest(testProperty.getName()));
        verify(propertyRepository, times(1)).save(any());
    }
}
