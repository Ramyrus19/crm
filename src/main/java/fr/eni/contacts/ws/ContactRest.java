package fr.eni.contacts.ws;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.contacts.bll.contact.ContactManager;
import fr.eni.contacts.bll.contact.ContactManagerException;
import fr.eni.contacts.bll.country.CountryManager;
import fr.eni.contacts.bo.Comment;
import fr.eni.contacts.bo.Contact;
import fr.eni.contacts.bo.Country;
import fr.eni.contacts.bo.Hobby;

@RestController
public class ContactRest {
	@Autowired
	private ContactManager contactManager;
	
	@Autowired
	private CountryManager countryManager;
	
	@GetMapping("/ws/contacts/{country}")
	public List<Contact> getAllByCountry(@PathVariable String country) throws ContactManagerException{
		Country c = countryManager.getByName(country);
		return contactManager.getByCountry(c);
	}
	
	@GetMapping("/ws/contacts/{country}/nocomments")
	public List<Contact> getAllByCountryWithoutComments(@PathVariable String country) throws ContactManagerException{
		return contactManager.getByCountryWithoutComments(country);
	}
	
	@GetMapping("/ws/contacts")
	public List<Contact> getAll(){
		return contactManager.getAll();
	}	

	@GetMapping("/ws/contacts/{id}/hobbies")
	public List<Hobby> getHobbies(@PathVariable Integer id){
		Contact c = contactManager.getById(id);
		return contactManager.getHobbies(c);
	}
	
	@GetMapping("/ws/contacts/{id}/comments")
	public List<Comment> getComments(@PathVariable Integer id){
		Contact c = contactManager.getById(id);
		return contactManager.getComments(c);
	}
}
