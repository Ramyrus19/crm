package fr.eni.contacts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.contacts.bll.contact.ContactManagerException;
import fr.eni.contacts.bo.City;
import fr.eni.contacts.bo.Client;
import fr.eni.contacts.bo.Comment;
import fr.eni.contacts.bo.Contact;
import fr.eni.contacts.bo.Country;
import fr.eni.contacts.bo.Hobby;

@SpringBootTest
class ContactManagerTest {

	@Autowired
	fr.eni.contacts.bll.contact.ContactManager manager;

	@Test
	@Transactional
	public void addTest() throws ContactManagerException {
		Client client = new Client("ENI Ecole Informatique", "Ecole qui forme aux métiers de l'informatique en continu ou en alternance");
		Country france = new Country("France");
		City quimper = new City("29000", "Quimper", france);
		Contact jean = new Contact("Cérien", "Jean", "0245789644", "1 rue du port", "jeancerien@outlook.com", quimper);
		jean.setClient(client);
		manager.add(jean);

		Contact fromManager = manager.getById(jean.getId());

		assertNotNull(fromManager);

		// insertion in the same city
		Contact vincent = new Contact("Tim", "Vincent", "0425369874", "7 rue du port", "vtim@gmail.com", quimper);
		vincent.setClient(client);
		manager.add(vincent);

		fromManager = manager.getById(jean.getId());
		assertNotNull(fromManager);

		// insertion in another city, same country
		City nantes = new City("44000", "Nantes", france);
		Contact alex = new Contact("Terrieur", "Alex", "0232124578", "4 rue du port", "alexterrieur@outlook.fr", nantes);
		alex.setClient(client);
		manager.add(alex);

		fromManager = manager.getById(alex.getId());
		assertNotNull(fromManager);

		// check if the relation with client is created
		assertNotNull(fromManager.getClient());
	}

	@Test
	@Transactional
	public void deleteTest() throws ContactManagerException {
		Client client = new Client("ENI Ecole Informatique", "Ecole qui forme aux métiers de l'informatique en continu ou en alternance");
		Country france = new Country("France");
		City marseille = new City("13000", "Marseille", france);
		Contact jean = new Contact("Airienafaire", "Jean", "0145789633", "2 rue du port", "jeanairienafaire@gmail.com", marseille);
		jean.setClient(client);
		manager.add(jean);

		manager.delete(jean);

		Contact fromManager = manager.getById(jean.getId());
		assertNull(fromManager);

	}

	@Test
	@Transactional
	public void updateTest() throws ContactManagerException {
		Client client = new Client("ENI Ecole Informatique", "Ecole qui forme aux métiers de l'informatique en continu ou en alternance");
		Country france = new Country("France");
		City marseille = new City("13000", "Marseille", france);
		Contact jean = new Contact("Airienafaire", "Jean", "0145789633", "2 rue du port", "jeanairienafaire@gmail.com", marseille);
		jean.setClient(client);
		manager.add(jean);

		// modification du client
		Client newClient = new Client("New Client", "new client");
		jean.setClient(newClient);
		manager.update(jean);

		Contact fromManager = manager.getById(jean.getId());
		assertEquals(fromManager.getClient().getName(), "New Client");

	}

	@Test
	@Transactional
	public void hobbyTest() throws ContactManagerException {
		Client client = new Client("ENI Ecole Informatique", "Ecole qui forme aux métiers de l'informatique en continu ou en alternance");
		Country france = new Country("France");
		City marseille = new City("13000", "Marseille", france);
		Contact jean = new Contact("Airienafaire", "Jean", "0145789633", "2 rue du port", "jeanairienafaire@gmail.com", marseille);
		jean.setClient(client);
		manager.add(jean);

		Hobby kayak = new Hobby("kayak", "sport consistant à propulser des embarcations à l'aide d'une pagaie");
		Hobby planche = new Hobby("planche à voile", "sport de glisse pratiqué avec cette une planche à voile");

		manager.addHobby(kayak, jean);
		manager.addHobby(planche, jean);
		
		Contact fromManager = manager.getById(jean.getId());
		assertEquals(fromManager.getHobbies().size(), 2);
		
		assertEquals(fromManager.getHobbies().get(1).getNom(), "Code");
		

	}
	
	@Test
	@Transactional
	public void commentTest() throws ContactManagerException {
		Client client = new Client("ENI Ecole Informatique", "Ecole qui forme aux métiers de l'informatique en continu ou en alternance");
		Country france = new Country("France");
		City marseille = new City("13000", "Marseille", france);
		Contact jean = new Contact("Airienafaire", "Jean", "0145789633", "2 rue du port", "jeanairienafaire@gmail.com", marseille);
		jean.setClient(client);
		manager.add(jean);

		Comment c1 = new Comment("Java est Null");
		Comment c2 = new Comment("Java est trop de la balle");
		
		manager.addComment(c1, jean);
		manager.addComment(c2, jean);
		
		Contact fromManager = manager.getById(jean.getId());
		assertEquals(fromManager.getComments().size(), 2);
		
		assertEquals(fromManager.getComments().get(1).getText(), "Java est trop de la balle");
		

	}
}
