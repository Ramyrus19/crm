package fr.eni.contacts.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.eni.contacts.bo.Contact;
import fr.eni.contacts.bo.Country;
import fr.eni.contacts.bo.City;


public interface ContactDAO extends CrudRepository<Contact, Integer>{
	List<Contact> findByNom(String nom);
	List<Contact> findByNomAndPrenom(String nom, String prenom);
	List<Contact> findByVille(City city);
	List<Contact> findByNomAndAdresse(String nom, String adresse);

	@Query("select c from Contact c where c.ville.country = :country")
	List<Contact> findByCountry(@Param("country") Country country);
	
	@Query("from Contact c where c.ville.country.nom = :country and c.comments.size = 0")
	List<Contact> findByCountryNoComments(@Param("country") String country);
}
