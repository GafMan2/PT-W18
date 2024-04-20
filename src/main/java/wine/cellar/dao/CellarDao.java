package wine.cellar.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import wine.cellar.entity.Cellar;

public interface CellarDao extends JpaRepository<Cellar, Long> { 
	// @Query is used to specify a custom query in JPQL (Java Persistence Query Language)
	@Query("select c from Cellar c where c.owner.id = :ownerId") // This query selects a Cellar entity based on the owner's ID.
    Optional<Cellar> findByOwnerId(@Param("ownerId") Long ownerId);
}