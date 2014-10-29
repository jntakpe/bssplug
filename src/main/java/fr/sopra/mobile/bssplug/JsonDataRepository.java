package fr.sopra.mobile.bssplug;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ntakpe_j
 */
@Repository
public interface JsonDataRepository extends JpaRepository<JsonData, String> {


    JsonData findByServiceAndId(String service, String id);
}
