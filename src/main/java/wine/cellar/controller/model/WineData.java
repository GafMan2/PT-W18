package wine.cellar.controller.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wine.cellar.entity.Wine;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WineData {
    private Long wineId;
    private String wineName;
    private Integer vintageYear;
    private String varietal;
    private String color;
    private String appellation;
    private Integer quantity;
    private BigDecimal bottlePrice;
    private String tastingNote;
    private List<Long> cellarIds = new ArrayList<>();

    public WineData(Wine wine) {
        this.wineId = wine.getWineId();
        this.wineName = wine.getWineName();
        this.vintageYear = wine.getVintageYear();
        this.varietal = wine.getVarietal();
        this.color = wine.getColor();
        this.appellation = wine.getAppellation();
        this.quantity = wine.getQuantity();
        this.bottlePrice = wine.getBottlePrice();
        this.tastingNote = wine.getTastingNote();
        
        }
    }
