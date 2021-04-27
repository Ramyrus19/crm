package fr.eni.contacts.bll.contact;

import java.util.List;

import fr.eni.contacts.bo.Contact;
import fr.eni.contacts.bo.Country;
import fr.eni.contacts.bo.Hobby;
import fr.eni.contacts.bo.City;
import fr.eni.contacts.bo.Comment;


/**
 * Interface to manage contacts
 * 
 * @author ramon
 *
 */
public interface ContactManager {
	
	/**
	 * @param contact to be added in database
	 * @return the new contact
	 */
	public void add(Contact contact) throws ContactManagerException;
	
	/**
	 * @param contact to be updated
	 * @return the updated contact
	 */
	public void update(Contact contact) throws ContactManagerException;
	
	/**
	 * @param contact to be deleted
	 */
	public void delete(Contact contact) throws ContactManagerException;
	
	/**
	 * @param name
	 * @return a list of all found contacts
	 */
	public List<Contact> getByName(String name) throws ContactManagerException;
	
	/**
	 * @return a list of all contacts
	 */
	public List<Contact> getAll();
	
	/**
	 * @param ville
	 * @return a list of all contacts by city
	 */
	public List<Contact> getByCity(City city) throws ContactManagerException;
	
	/**
	 * @param country
	 * @return a list of all contacts by city
	 */
	public List<Contact> getByCountry(Country country) throws ContactManagerException;

	/**
	 * delete all old contacts
	 */
	public void deleteOldContacts();
	
	
	/**
	 * add a hobby to a contact
	 * @param hobby
	 */
	public void addHobby(Hobby hobby, Contact contact);
	
	
	/**
	 * @return a list of hobbies of the contact
	 */
	public List<Hobby> getHobbies(Contact contact);

	public Contact getById(Integer id);

	public void addComment(Comment comment, Contact contact);

	public List<Comment> getComments(Contact contact);

	public List<Contact> getByCountryWithoutComments(String country);
	

}
