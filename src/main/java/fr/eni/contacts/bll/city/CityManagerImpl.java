package fr.eni.contacts.bll.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.contacts.bo.City;
import fr.eni.contacts.dal.CityDAO;

@Service
public class CityManagerImpl implements CityManager{

	@Autowired
	private CityDAO dao;
	@Override
	public void add(City city) {
		dao.save(city);
	}

	@Override
	public void delete(City city) {
		dao.delete(city);
	}

	@Override
	public void update(City city) {
		dao.save(city);
		
	}

	@Override
	public List<City> getAll() {
		return (List<City>) dao.findAll();
	}

	@Override
	public City getById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public void removeFromId(Integer id) {
		dao.deleteById(id);
	}

}
