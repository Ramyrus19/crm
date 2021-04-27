package fr.eni.contacts.ws;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eni.contacts.bll.contact.ContactManager;
import fr.eni.contacts.bll.contact.ContactManagerException;
import fr.eni.contacts.bo.City;
import fr.eni.contacts.bo.Comment;
import fr.eni.contacts.bo.Contact;
import fr.eni.contacts.bo.Country;
import fr.eni.contacts.bo.Hobby;

@Component
public class InitBDD {
	@Autowired
	private ContactManager contactManager;
	
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
			
			Hobby kayak = new Hobby("kayak", "sport consistant à propulser des embarcations à l'aide d'une pagaie");
			Hobby planche = new Hobby("planche à voile", "sport de glisse pratiqué avec cette une planche à voile");

			contactManager.addHobby(kayak, alain);
			contactManager.addHobby(planche, alain);
			
			Comment c1 = new Comment("Java est Null");
			Comment c2 = new Comment("Java est trop de la balle");
			
			contactManager.addComment(c1, alain);
			contactManager.addComment(c2, alain);

		}
	}
}
