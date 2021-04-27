package fr.eni.contacts.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.eni.contacts.bo.Contact;
import fr.eni.contacts.bo.Hobby;


public interface HobbyDAO extends CrudRepository<Hobby, Integer> {
	@Query("SELECT c.hobbies FROM Contact c WHERE c = :contact")
    public List<Hobby> findByContact(@Param("contact") Contact contact);
}
