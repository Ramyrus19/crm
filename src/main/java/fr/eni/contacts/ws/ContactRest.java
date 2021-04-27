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
			City lyon = new City("69000", "Lyon");
			lyon.setCountry(france);
			City paris = new City("75000", "Paris");
			paris.setCountry(france);
			City rennes = new City("35000", "Rennes");
			rennes.setCountry(france);
			City nantes = new City("44000", "Nantes");
			nantes.setCountry(france);
			City montpellier = new City("34000", "Montpellier");
			montpellier.setCountry(france);
			City marseille = new City("13000", "Marseille");
			marseille.setCountry(france);
			City bucarest = new City("99000", "Bucarest");
			bucarest.setCountry(roumanie);
			
			Contact contact1 = new Contact("Cérien", "Jean", "0245789644", "1 rue du port", "jeancerien@outlook.com", lyon);
			Contact contact2 = new Contact("Airienafaire", "Jean", "0145789633", "2 rue du port", "jeanairienafaire@gmail.com", paris);
			Contact contact3 = new Contact("Terrieur", "Alain", "0321457896", "3 rue du port", "alainterrieur@gmail.com", rennes);
			Contact contact4 = new Contact("Terrieur", "Alex", "0232124578", "4 rue du port", "alexterrieur@outlook.fr", nantes);
			Contact contact5 = new Contact("Kiroul", "Pierre", "0236987456", "5 rue du port", "pierrekiroul@gmail.com", marseille);
			Contact contact6 = new Contact("Monmanteaux", "Jimmy", "9874562310", "6 rue du port", "jmanteau@gmail.com", bucarest);
			Contact contact7 = new Contact("Tim", "Vincent", "0425369874", "7 rue du port", "vtim@gmail.com", montpellier);
			Contact contact8 = new Contact("Tenrien", "Jean", "0123456789", "8 rue du port", "tj@gmail.com", marseille);
			
			// add()
			contactManager.add(contact1);
			contactManager.add(contact2);
			contactManager.add(contact3);
			contactManager.add(contact4);
			contactManager.add(contact5);
			contactManager.add(contact6);
			contactManager.add(contact7);
			contactManager.add(contact8);
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
