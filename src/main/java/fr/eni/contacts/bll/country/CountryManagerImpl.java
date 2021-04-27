package fr.eni.contacts.bll.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.contacts.bo.Country;
import fr.eni.contacts.dal.CountryDAO;

@Service
public class CountryManagerImpl implements CountryManager {
	@Autowired
	private CountryDAO dao;

	@Override
	public Country getByName(String name) {
		
		return dao.findByName(name);
	}
}
