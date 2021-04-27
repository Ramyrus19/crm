package fr.eni.contacts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.contacts.bll.comment.CommentManager;
import fr.eni.contacts.bll.contact.ContactManager;
import fr.eni.contacts.bll.contact.ContactManagerException;
import fr.eni.contacts.bo.Comment;
import fr.eni.contacts.bo.Contact;
import fr.eni.contacts.bo.Country;
import fr.eni.contacts.bo.Hobby;
import fr.eni.contacts.bo.City;

@SpringBootTest
class ContactsApplicationTests {
	
	@Autowired
	private ContactManager contactManager;
	
	@Autowired
	private CommentManager commentManager;

	@Test
	void contextLoads() throws ContactManagerException {

		Country france = new Country("France");
		Country roumanie = new Country("Roumanie");
		City lyon = new City("69000", "Lyon", france);
		City paris = new City("75000", "Paris", france);
		City rennes = new City("35000", "Rennes", france);
		City nantes = new City("44000", "Nantes", france);
		City montpellier = new City("34000", "Montpellier", france);
		City marseille = new City("13000", "Marseille", france);
		City bucarest = new City("99000", "Bucarest", roumanie);
		
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
		
		// update()
//		contact1.setDateAjout(LocalDate.parse("21/01/2015", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//		contact4.setDateAjout(LocalDate.parse("05/02/2013", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//		contact7.setDateAjout(LocalDate.parse("21/01/2008", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//		contact8.setDateAjout(LocalDate.parse("14/06/2017", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//		contactManager.update(contact1);
//		contactManager.update(contact4);
//		contactManager.update(contact7);
//		contactManager.update(contact8);
		
//		contact1.setAdresse("1 rue des jonquilles");
//		contactManager.update(contact1);
		
		//delete contacts older than 3 years
//		contactManager.deleteOldContacts();
//		
//		// delete()
//		contactManager.delete(contact5);
		
		// getByName()
//		System.out.println(contactManager.getByName("Terrieur"));
		// get all contacts from Marseille
//		System.out.println(contactManager.getByCity("Marseille"));
		
//		System.out.println(contact1.getVille());
//		System.out.println(contactManager.getByCity(marseille));
		
//		Comment c1 = new Comment("This is the first comment of user 1");
//		Comment c2 = new Comment("This is the second comment of user 1");
//		commentManager.add(c1, contact1);
//		commentManager.add(c2, contact1);
//		
//		System.out.println(commentManager.getByContact(contact1));
//		
//		System.out.println(commentManager.getAll());
		
		// addHobby()
		Hobby kayak = new Hobby("kayak", "sport consistant à propulser des embarcations à l'aide d'une pagaie");
		contactManager.addHobby(kayak, contact1);
		
		Hobby planche = new Hobby("planche à voile", "sport de glisse pratiqué avec cette une planche à voile");
		contactManager.addHobby(planche, contact1);
		
		Hobby read = new Hobby("reading", "reading books");
		contactManager.addHobby(read, contact1);
		
//		System.out.println(contactManager.getHobbies(contact1));
		
		//get all contacts from France
//		System.out.println(contactManager.getByCountry(france));
		
	}

}
