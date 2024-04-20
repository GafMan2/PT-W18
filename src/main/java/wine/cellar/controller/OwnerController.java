package wine.cellar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import wine.cellar.entity.Cellar;
import wine.cellar.entity.Owner;
import wine.cellar.entity.Wine;
import wine.cellar.service.WineCellarService;

@RestController
@RequestMapping("wine_cellar/owners")
@Slf4j
public class OwnerController {

	private WineCellarService wineCellarService;

	public OwnerController(WineCellarService wineCellarService) { // This is the constructor of the OwnerController class
		this.wineCellarService = wineCellarService; // it takes an instance of WineCellarService as a parameter
	}

	@PostMapping // This annotation is used to map HTTP POST requests, in this case CREATE a new owner
	public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
		Owner savedOwner = wineCellarService.createOwner(owner);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedOwner);
	}

	@PostMapping("/{ownerId}/cellars") // This method is to CREATE a new cellar
	public ResponseEntity<Cellar> createCellarForOwner(@PathVariable Long ownerId, @RequestBody Cellar cellar) {
		Cellar newCellar = wineCellarService.createCellarForOwner(ownerId, cellar);
		return ResponseEntity.status(HttpStatus.CREATED).body(newCellar);
	}

	@PostMapping("/{ownerId}/wines") // This method is to CREATE a new wine in a cellar
	public ResponseEntity<String> addWineToCellar(@PathVariable Long ownerId, @RequestBody Wine wine) {
		try {
			Wine savedWine = wineCellarService.addWineToCellar(ownerId, wine);
			String response = "Cheers! Your wine was added successfully. \n"
					+ String.format("%d %s %s, %s", savedWine.getVintageYear(), savedWine.getWineName(),
							savedWine.getVarietal(), savedWine.getAppellation());
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to add wine: " + e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to add wine: " + e.getMessage());
		}
	}

	@GetMapping("/{ownerId}/wines") // This annotation is used to map HTTP GET requests, in this case retrieve a list of wines
	public ResponseEntity<List<Wine>> getWinesByOwner(@PathVariable Long ownerId) {
		List<Wine> wines = wineCellarService.getWinesByOwner(ownerId);
		if (wines.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(wines);
	}	

	@GetMapping // This method retrieves a list of owners
	@ResponseStatus(HttpStatus.OK)
	public List<Owner> getAllOwners() {
		log.info("Retrieving all owners");
		return wineCellarService.getAllOwners();
	}

	@GetMapping("/{ownerId}") // This method retrieves a specific owner by Id
	@ResponseStatus(HttpStatus.OK)
	public Owner findOwnerById(@PathVariable Long ownerId) {
		log.info("Retrieving owner with ID={}", ownerId);
		return wineCellarService.findOwnerById(ownerId);
	}
	
	@PutMapping("/{ownerId}/wines/{wineName}") // This annotation is used to map HTTP PUT requests, in this case update a wine
	public ResponseEntity<String> updateWineByName(@PathVariable Long ownerId, @PathVariable String wineName,
			@RequestBody Wine wineDetails) {
		try {
			Wine updatedWine = wineCellarService.updateWineByName(ownerId, wineName, wineDetails);
			return ResponseEntity.ok("Cheers! Your wine updated successfully.: " + updatedWine.getWineName());
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error updating wine: " + e.getMessage());
		}
	}

	@PutMapping("/{ownerId}") // This method updates an owner by ID
	@ResponseStatus(HttpStatus.OK)
	public Owner updateOwner(@PathVariable Long ownerId, @RequestBody Owner updatedOwner) {
	    log.info("Updating owner with ID={}", ownerId);
	    // Ensure that the updatedOwner object is passed correctly to the service layer.
	    return wineCellarService.updateOwner(ownerId, updatedOwner);
	}

	@DeleteMapping("/{ownerId}") // This annotation is used to map HTTP DELETE requests, in this case an owner
	public ResponseEntity<Map<String, String>> deleteOwner(@PathVariable Long ownerId) {
		boolean deleted = wineCellarService.deleteOwner(ownerId);
		if (deleted) {
			Map<String, String> response = new HashMap<>();
			response.put("message", "Owner with ID " + ownerId + " has been successfully deleted.");
			return ResponseEntity.ok(response);
		} else {
			Map<String, String> response = new HashMap<>();
			response.put("message", "Owner with ID " + ownerId + " not found, no deletion performed.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}
}
