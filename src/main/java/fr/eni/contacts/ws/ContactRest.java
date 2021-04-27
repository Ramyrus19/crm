package fr.eni.contacts.ws;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.contacts.bll.contact.ContactManager;
import fr.eni.contacts.bll.contact.ContactManagerException;
import fr.eni.contacts.bll.country.CountryManager;
import fr.eni.contacts.bo.City;
import fr.eni.contacts.bo.Contact;
import fr.eni.contacts.bo.Country;

@RestController
public class ContactRest {
	@Autowired
	private ContactManager contactManager;
	
	@Autowired
	private CountryManager countryManager;
	
	@PostConstruct
	private void init() throws IOException, ContactManagerException{
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream("/application.properties"));
		if ("create".equals(properties.getProperty("spring.jpa.hibernate.ddl-auto"))) {
			Country france = new Country("France");
			Country roumanie = new Country("Roumanie");
			City rennes = new City("35000", "Rennes", france);
			City nantes = new City("44000", "Nantes", france);
			City bucarest = new City("99000", "Bucarest", roumanie);
			
			Contact alain = new Contact("Terrieur", "Alain", "0321457896", "3 rue du port", "alainterrieur@gmail.com", rennes);
			Contact alex = new Contact("Terrieur", "Alex", "0232124578", "4 rue du port", "alexterrieur@outlook.fr", nantes);
			Contact jimmy = new Contact("Monmanteaux", "Jimmy", "9874562310", "6 rue du port", "jmanteau@gmail.com", bucarest);
			
			// add()
			contactManager.add(alain);
			contactManager.add(alex);
			contactManager.add(jimmy);

		}
	}
	
	@GetMapping("/ws/contacts/{country}")
	public List<Contact> getAllByCountry(@PathVariable String country) throws ContactManagerException{
		Country c = countryManager.getByName(country);
		return contactManager.getByCountry(c);
	}
	
	@GetMapping("/ws/contacts")
	public List<Contact> getAll(){
		return contactManager.getAll();
	}

}
