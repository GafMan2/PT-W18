package wine.cellar.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import wine.cellar.entity.Owner;

public interface OwnerDao extends JpaRepository<Owner, Long> {

}
