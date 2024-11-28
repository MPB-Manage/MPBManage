package dk.mpb.manage.project.repository;

import dk.mpb.manage.project.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
}
