package fr.eni.contacts.dal;

import org.springframework.data.repository.CrudRepository;

import fr.eni.contacts.bo.City;

public interface CityDAO extends CrudRepository<City, Integer> {

}
