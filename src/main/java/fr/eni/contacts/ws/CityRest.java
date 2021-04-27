package fr.eni.contacts.ws;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.contacts.bll.city.CityManager;
import fr.eni.contacts.bo.City;

@RestController
public class CityRest {
	@Autowired
	private CityManager cityManager;
	
	@GetMapping("/ws/cities")
	public List<City> getAll(){
		return cityManager.getAll();
	}
}
