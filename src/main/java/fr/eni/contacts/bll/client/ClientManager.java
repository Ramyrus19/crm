package fr.eni.contacts.bll.client;

import java.util.List;

import fr.eni.contacts.bo.Client;

public interface ClientManager {
	/**
	 * Add client
	 * @param client
	 */
	public void add(Client client);
	
	/**
	 * Delete client
	 * @param client
	 */
	public void delete(Client client);
	
	/**
	 * update client
	 * @param client
	 */
	public void update(Client client);
	
	/**
	 * Get all clients
	 * @return list clients
	 */
	public List<Client> getAll();
	
	/**
	 * get client by id
	 * @param id 
	 * @return client or null if not existing
	 */
	public Client getById(Integer id);

	public void removeFromId(Integer id);
}
