package fr.eni.contacts.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "Le nom doit être saisie")
	private String name;
	private String description;
	
	@OneToMany(mappedBy = "client")
	@JsonManagedReference
	private List<Contact> lstContact = new ArrayList<>();

	public Client(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	
}
