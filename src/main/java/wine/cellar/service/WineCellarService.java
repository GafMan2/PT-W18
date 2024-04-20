package wine.cellar.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wine.cellar.dao.CellarDao;
import wine.cellar.dao.OwnerDao;
import wine.cellar.dao.WineDao;
import wine.cellar.entity.Cellar;
import wine.cellar.entity.Owner;
import wine.cellar.entity.Wine;
import wine.cellar.utility.UserCredentialsGenerator;

@Service // This is a Spring annotation that marks the class WineCellarService as a service class. 
public class WineCellarService {

	@Autowired // This annotation is used for dependency injection in Spring 
	private WineDao wineDao; 

	@Autowired
	private CellarDao cellarDao;

	@Autowired
	private OwnerDao ownerDao; // this is the DAO class for owner, it contains methods to access and manipulate owner data in the database.

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public Owner createOwner(Owner owner) { // for this method you need to enter data into both fields but only password
											// is randomized
		if (owner.getUsername() == null || owner.getPassword() == null) { // This checks if username and password are
																			// null
			owner.setUsername(UserCredentialsGenerator.generateRandomUsername());
			owner.setPassword(UserCredentialsGenerator.generateRandomPassword());
		}
		owner.setPassword(passwordEncoder.encode(owner.getPassword())); // I set it up to only encode the password

		return ownerDao.save(owner); // Saves the owner
	}

	public Owner updateOwner(Long ownerId, Owner updatedOwnerDetails) {
	    Owner owner = ownerDao.findById(ownerId).orElseThrow(
	        () -> new NoSuchElementException("Owner with ID=" + ownerId + " not found. Please try again."));

	    owner.setUsername(updatedOwnerDetails.getUsername()); // This line sets the username to the new username provided
	    
	    if (updatedOwnerDetails.getPassword() == null) { // This segment checks if the password provided is null.	    	 
	        String randomPassword = UserCredentialsGenerator.generateRandomPassword();
	        owner.setPassword(passwordEncoder.encode(randomPassword));
	    } else {  //If it is null, the method generates a random password
	        owner.setPassword(passwordEncoder.encode(updatedOwnerDetails.getPassword()));
	    }

	    return ownerDao.save(owner);
	}

	@Transactional
	public Cellar createCellarForOwner(Long ownerId, Cellar cellar) { // This creates a new cellar for an existing owner

		Owner owner = ownerDao.findById(ownerId) // Retrieve owner by Id or throw not found exception
				.orElseThrow(() -> new NoSuchElementException("Owner not found with ID: " + ownerId));

		if (owner.getCellar() != null) { // Checks if the owner already has a cellar
			throw new IllegalStateException("Owner already has a cellar assigned");
		}
		cellar.setOwner(owner); // Sets the owner to the cellar

		return cellarDao.save(cellar); // Saves the new cellar to the database
	}

	@Transactional(readOnly = true)
	public List<Owner> getAllOwners() { // Retrieves a list of all owners
		return ownerDao.findAll();
	}

	@Transactional
	public boolean deleteOwner(Long ownerId) { // this method deletes the owner, their cellar and all wine
		return ownerDao.findById(ownerId).map(owner -> {
			ownerDao.delete(owner);
			return true;
		}).orElse(false);
	}

	@Transactional(readOnly = true) // (readOnly = true) tells the database that no modifications will be made
									// during this transaction
	public Owner findOwnerById(Long ownerId) { // this method retrieves a specific owner by their ownerId
		return ownerDao.findById(ownerId).orElseThrow(
				() -> new NoSuchElementException("Owner with ID=" + ownerId + " not found. Please try again."));
	}

	@Transactional(readOnly = true)
	public List<Cellar> getAllCellars() { // this method retrieves a list of all owners
		return cellarDao.findAll();
	}

	@Transactional
	public Wine addWineToCellar(Long ownerId, Wine wine) throws NoSuchElementException { 
	// This method is for adding a new wine to an owner cellar
		
		Owner owner = ownerDao.findById(ownerId)
				.orElseThrow(() -> new NoSuchElementException("Owner not found with ID: " + ownerId));
		Cellar cellar = owner.getCellar();
		if (cellar == null) {
			throw new NoSuchElementException("Cellar not found for owner with ID: " + ownerId);
		}
		wine.setCellar(cellar);
		return wineDao.save(wine); // Saves and return the updated wine object
	}

	public Wine updateWineByName(Long ownerId, String wineName, Wine wineDetails) {
	// this method is used for things like adding tasting notes, changing number of bottles left
																					
		ownerDao.findById(ownerId)
				.orElseThrow(() -> new NoSuchElementException("Owner with id " + ownerId + " not found"));

		Wine wine = wineDao.findByOwnerAndWineName(ownerId, wineName).orElseThrow(
				() -> new NoSuchElementException("Wine named " + wineName + " not found for owner with id " + ownerId));

		updateWineFields(wine, wineDetails);

		return wineDao.save(wine);
	}

	private void updateWineFields(Wine existingWine, Wine newDetails) {
		existingWine.setVintageYear(newDetails.getVintageYear());
		existingWine.setVarietal(newDetails.getVarietal());
		existingWine.setColor(newDetails.getColor());
		existingWine.setAppellation(newDetails.getAppellation());
		existingWine.setQuantity(newDetails.getQuantity());
		existingWine.setBottlePrice(newDetails.getBottlePrice());
		existingWine.setTastingNote(newDetails.getTastingNote());
	}

	@Transactional(readOnly = true)
	public List<Wine> getWinesByOwner(Long ownerId) { // this method retrieves a list of an owner's wines
		return ownerDao.findById(ownerId).map(owner -> {
			return wineDao.findByCellarOwnerOwnerId(ownerId);
		}).orElseThrow(() -> new NoSuchElementException("Owner with id " + ownerId + " not found"));
	}
}