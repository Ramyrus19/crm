package fr.eni.contacts.bll.client;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.contacts.bo.Client;
import fr.eni.contacts.dal.ClientDAO;

@Service
public class ClientManagerImpl implements ClientManager{

	@Autowired
	private ClientDAO dao;
	
	@Override
	@Transactional
	public void add(Client client) {
		dao.save(client);
	}

	@Override
	public void delete(Client client) {
		dao.delete(client);
		
	}

	@Override
	public void update(Client client) {
		dao.save(client);
		
	}

	@Override
	public List<Client> getAll() {
		return (List<Client>) dao.findAll();
	}

	@Override
	public Client getById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public void removeFromId(Integer id) {
		dao.deleteById(id);
	}

}
