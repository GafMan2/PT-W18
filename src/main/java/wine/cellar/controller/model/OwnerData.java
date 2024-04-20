package wine.cellar.controller.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import wine.cellar.entity.Cellar;
import wine.cellar.entity.Owner;
import wine.cellar.entity.Wine;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerData {

	private Long ownerId;
	private String username;
	private String password;

	@Value
	public 
	static class CellarResponse {
		private Long cellarId;
		private String cellarName;
		private String location;
		private String capacity;
		private Owner owner;
		private List<Wine> wines;

		public CellarResponse(Cellar cellar) {
			this.cellarId = cellar.getCellarId();
			this.cellarName = cellar.getCellarName();
			this.location = cellar.getLocation();
			this.capacity = cellar.getCapacity();
			this.owner = cellar.getOwner();
			this.wines = cellar.getWines();
		}
	}
}
