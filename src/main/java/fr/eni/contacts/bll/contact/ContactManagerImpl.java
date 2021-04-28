/**
 * 
 */
package fr.eni.contacts.bll.contact;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.contacts.bo.Contact;
import fr.eni.contacts.bo.Country;
import fr.eni.contacts.bo.Hobby;
import fr.eni.contacts.bo.City;
import fr.eni.contacts.bo.Comment;
import fr.eni.contacts.dal.ContactDAO;
import fr.eni.contacts.dal.CountryDAO;
import fr.eni.contacts.dal.HobbyDAO;
import fr.eni.contacts.dal.CityDAO;
import fr.eni.contacts.dal.CommentDAO;

/**
 * @author ramon
 *
 */
@Service
public class ContactManagerImpl implements ContactManager {
	@Autowired
	private ContactDAO daoContact;
	@Autowired
	private CityDAO daoVille;
	@Autowired
	private HobbyDAO daoHobby;
	@Autowired
	private CountryDAO daoCountry;
	
	@Autowired
	private CommentDAO daoComment;

	@Transactional
	@Override
	public void add(Contact contact) throws ContactManagerException {
		// check if the contact is not already existing
		List<Contact> identique = daoContact.findByNomAndPrenom(contact.getNom(),contact.getPrenom());
		List<Contact> famille = daoContact.findByNomAndAdresse(contact.getNom(), contact.getAdresse());
		if(!identique.isEmpty()) {
			throw new ContactManagerException("Contact already exists !");
		}
		if (famille.size() >= 4) {
			throw new ContactManagerException("Pas plus de 5 members de la même famille !");
		}
		daoCountry.save(contact.getVille().getCountry());
		daoVille.save(contact.getVille());
		contact.setDateAjout(LocalDate.now());
		daoContact.save(contact);	
	}

	@Override
	@Transactional
	public void update(Contact contact) throws ContactManagerException {
		try {
			daoVille.save(contact.getVille());
			daoContact.save(contact);
		}catch (Exception e) {
			throw new ContactManagerException("Impossible to update !");
		} 
	}

	@Override
	@Transactional
	public void delete(Contact contact) throws ContactManagerException {
		try {
			daoContact.delete(contact);
		} catch (Exception e) {
			throw new ContactManagerException("Impossible to delete the contact !");
		}
	}

	@Override
	public List<Contact> getByName(String name) throws ContactManagerException {
		List<Contact> contacts = null;
		try {
			contacts = daoContact.findByNom(name);
		} catch (Exception e) {
			throw new ContactManagerException("Impossible to retrieve the list of contacts by name !");
		}

		return contacts;
	}

	@Override
	public List<Contact> getByCity(City city) throws ContactManagerException {
		return daoContact.findByVille(city);
	}

	
	private int getDifferenceInYears(LocalDate today, LocalDate dateAjout) {
	    Period period = Period.between(today, dateAjout);
	    int diff = Math.abs(period.getYears());

	    return diff;
	}
	
	@Override
	public void deleteOldContacts() {
		Iterable<Contact> contacts = daoContact.findAll();
		for (Contact contact : contacts) {
			if (getDifferenceInYears(LocalDate.now(), contact.getDateAjout()) > 3) {
				daoContact.delete(contact);
			}
		}
		
	}
	
	@Override
	@Transactional
	public void addHobby(Hobby hobby, Contact contact) {
		contact = daoContact.findById(contact.getId()).get();
        contact.getHobbies().add(hobby);
        daoHobby.save(hobby);   
        daoContact.save(contact);
	}

	@Override
	public List<Hobby> getHobbies(Contact contact) {
		return daoHobby.findByContact(contact);
	}

	@Override
	public List<Contact> getByCountry(Country country) throws ContactManagerException {
		return daoContact.findByCountry(country);
	}

	@Override
	public List<Contact> getAll() {
		
		return (List<Contact>) daoContact.findAll();
	}

	@Override
	public Contact getById(Integer id) {
		return daoContact.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void addComment(Comment comment, Contact contact) {
		contact.getComments().add(comment);
		comment.setContact(contact);
		
		daoComment.save(comment);
		daoContact.save(contact);
		
	}

	@Override
	public List<Comment> getComments(Contact contact) {
		return daoComment.findByContact(contact);
	}

	@Override
	public List<Contact> getByCountryWithoutComments(String country) {
//		Country c = daoCountry.findByName(country);
//		List<Contact> contacts = daoContact.findByCountry(c);
//		List<Contact> withoutComments = contacts.stream().filter(contact -> contact.getComments().isEmpty()).collect(Collectors.toList());
		
		return daoContact.findByCountryNoComments(country);
	}

}
