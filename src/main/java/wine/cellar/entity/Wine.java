package wine.cellar.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wineId;

    @EqualsAndHashCode.Exclude
    private String wineName;

    @EqualsAndHashCode.Exclude
    private Integer vintageYear;

    @EqualsAndHashCode.Exclude
    private String varietal;

    @EqualsAndHashCode.Exclude
    private String color;

    @EqualsAndHashCode.Exclude
    private String appellation;

    @EqualsAndHashCode.Exclude
    private Integer quantity;

    @EqualsAndHashCode.Exclude
    private BigDecimal bottlePrice;

    @EqualsAndHashCode.Exclude
    private String tastingNote;

    // Single association to Cellar
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}) // This references the singular cellar associated with this wine
    @JoinColumn(name = "cellar_id")
    @JsonIgnoreProperties("wines") // Prevents recursion by ignoring the list of wines in Cellar
    private Cellar cellar;
}