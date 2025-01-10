package dk.mpb.manage.project.repository;

import dk.mpb.manage.project.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *  Client repository
 * */
public interface ClientRepository extends JpaRepository<Client, Integer> {
    /**
     *  Find all clients by property id
     * */
    List<Client> findAllByPropertyId(int id);
}
