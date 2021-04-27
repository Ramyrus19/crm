package fr.eni.contacts.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String cp;
	private String nom;
	
	@ManyToOne
	private Country country;

	public City(String cp, String nom, Country country) {
		super();
		this.cp = cp;
		this.nom = nom;
		this.country = country;
	}

	
}
