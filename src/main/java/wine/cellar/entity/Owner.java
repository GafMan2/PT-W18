package wine.cellar.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ownerId;

	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Invalid email format")
	@Column(unique = true, nullable = false)
	private String username;
	private String password;

	// Declares a one-to-one relationship with Cellars
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("owner") // Added this because I was getting recursion when retrieving owner data
	private Cellar cellar;

}
