package fr.eni.contacts.ws;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.contacts.bll.city.CityManager;
import fr.eni.contacts.bo.City;
import fr.eni.contacts.bo.Country;

@RestController
public class CityRest {
	@Autowired
	private CityManager cityManager;
	
	@PostConstruct
	private void init() throws IOException{
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream("/application.properties"));
		if ("create".equals(properties.getProperty("spring.jpa.hibernate.ddl-auto"))) {
			Country france = new Country("France");
			Country roumanie = new Country("Roumanie");
			City rennes = new City("35000", "Rennes", france);
			City nantes = new City("44000", "Nantes", france);
			City bucarest = new City("99000", "Bucarest", roumanie);
			
			cityManager.add(rennes);
			cityManager.add(nantes);
			cityManager.add(bucarest);
		}
	}
	
	@GetMapping("/ws/cities")
	public List<City> getAll(){
		return cityManager.getAll();
	}
}
