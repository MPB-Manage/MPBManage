package dk.mpb.manage.project.repository;

import dk.mpb.manage.project.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findAllByPropertyId(int id);
}
