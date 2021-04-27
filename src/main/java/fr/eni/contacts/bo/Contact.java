package fr.eni.contacts.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "contact")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 50)
	private String nom;
	private String prenom;
	private String telephone;
	private String adresse;
	private String email;
	private LocalDate dateAjout;
	
	@ManyToOne
	private City ville;
	
	@OneToMany(mappedBy = "contact")
	@JsonBackReference
	private List<Comment> comments = new ArrayList<>();
	
	@ManyToMany
	private List<Hobby> hobbies = new ArrayList<>();
	
	@ManyToOne
	private Client client;

	public Contact(String nom, String prenom, String telephone, String adresse, String email,
			City ville) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.adresse = adresse;
		this.email = email;
		this.ville = ville;
	}

}
