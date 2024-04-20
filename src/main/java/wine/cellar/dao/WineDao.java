package wine.cellar.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import wine.cellar.entity.Wine;

@Repository
public interface WineDao extends JpaRepository<Wine, Long> {
		
	// Method to find a single wine by owner ID and wine name
    @Query("SELECT w FROM Wine w WHERE w.cellar.owner.ownerId = :ownerId AND lower(w.wineName) = lower(:wineName)")
    Optional<Wine> findByOwnerAndWineName(@Param("ownerId") Long ownerId, @Param("wineName") String wineName);

    // This method retrieves all wines in an owner's cellar
    List<Wine> findByCellarOwnerOwnerId(Long ownerId);
	}
