package fr.eni.contacts.dal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.eni.contacts.bo.Country;

public interface CountryDAO extends CrudRepository<Country, Integer>{

	@Query("select c from Country c where c.nom = :name")
	public Country findByName(@Param("name") String name);

}
