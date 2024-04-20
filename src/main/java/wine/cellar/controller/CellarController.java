package wine.cellar.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import wine.cellar.controller.model.CellarData;
import wine.cellar.entity.Cellar;
import wine.cellar.service.WineCellarService;

@RestController // This annotation indicates that the class is a Spring controller with its methods returning data directly as HTTP responses
@RequestMapping("/wine_cellar/cellars") // This sets the base path for all request mappings
@Slf4j // A Lombok annotation that provides a logger variable named log that allows logging events within this class
public class CellarController {

	@Autowired // This is used field injection indirectly through the constructor, Spring injects an instance of WineCellarService into this controller when it's created
	private final WineCellarService wineCellarService;

	public CellarController(WineCellarService wineCellarService) { // This is the constructor used for dependency injection, 
		this.wineCellarService = wineCellarService;				   // it takes an instance of WineCellarService as a parameter	
	}

	@GetMapping //This annotation is used to map HTTP GET requests
	@ResponseStatus(HttpStatus.OK) // This annotation declares that HTTP response status should be 200 OK if the method completes successfully
	public List<CellarData> getAllCellars() {
		log.info("Retrieving all cellars"); // This is useful for debugging, this logs the action of retrieving all cellars to my application's log
		List<Cellar> cellars = wineCellarService.getAllCellars();
		return cellars.stream().map(CellarData::new) // I used stream and map to process the list of cellar entities												
				.collect(Collectors.toList()); // here I am collecting the results, after transformation, the stream of CellarData objects is collected back into a list
	}
}