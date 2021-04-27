package fr.eni.contacts.bll.city;

import java.util.List;

import fr.eni.contacts.bo.City;

public interface CityManager {
	public void add(City city);
	public void delete(City city);
	public void update(City city);
	public List<City> getAll();
	public City getById(Integer id);
	public void removeFromId(Integer id);
}
