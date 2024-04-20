package wine.cellar.controller.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wine.cellar.entity.Cellar;
import wine.cellar.entity.Owner;
import wine.cellar.entity.Wine;

@Data // a Lombok annotation that automatically generates boilerplate code such as getters, setters, etc.
@NoArgsConstructor // another Lombok annotation that generates a no-argument constructor for the class
@AllArgsConstructor // a Lombok annotation that generates a constructor that includes all the fields in the class
public class CellarData {
    private Long cellarId;
    private String cellarName;
    private String location;
    private String capacity;
    private OwnerData owner;
    private List<WineData> wines = new ArrayList<>(); 

    public CellarData(Cellar cellar) { // This is a custom constructor that takes an instance of Cellar
        this.cellarId = cellar.getCellarId(); // and maps its properties to the properties of the CellarData DTO
        this.cellarName = cellar.getCellarName();
        this.location = cellar.getLocation();
        this.capacity = cellar.getCapacity();

        if (cellar.getOwner() != null) {
            this.owner = new OwnerData();
            Owner ownerEntity = cellar.getOwner();
            this.owner.setOwnerId(ownerEntity.getOwnerId());
            this.owner.setUsername(ownerEntity.getUsername());
        }

        if (cellar.getWines() != null) {  
            for (Wine wine : cellar.getWines()) { 
                this.wines.add(new WineData(wine));
            }
        }
    }
}

