package fr.eni.contacts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.contacts.bll.client.ClientManager;
import fr.eni.contacts.bo.Client;

@SpringBootTest
class ClientManagerTest {

	@Autowired
	ClientManager manager;
	
	@Test
	@Transactional
	public void classicProcess() {
		Client client = new Client("Super Client", "The best of the Best");
		
		// create
		manager.add(client);
		Client fromManager = manager.getById(client.getId());
		System.out.println(">>>>Création d'un client : "+fromManager);
		assertNotNull(fromManager);
		
		// update
		client.setDescription("Ah ben non, il pas bon");
		manager.update(client);
		fromManager = manager.getById(client.getId());
		System.out.println(">>>>Modification d'un client : "+fromManager);
		assertEquals(fromManager.getDescription(), "Ah ben non, il pas bon");
	
		// getAll
		assertEquals(manager.getAll().size(), 1);
		
		// delete
		manager.delete(client);
		fromManager = manager.getById(client.getId());
		System.out.println(">>>>Suppression d'un client (null) : "+fromManager);
		assertNull(fromManager);

	
	}
}
