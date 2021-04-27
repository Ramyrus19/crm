package fr.eni.contacts.dal;

import org.springframework.data.repository.CrudRepository;

import fr.eni.contacts.bo.Client;

public interface ClientDAO extends CrudRepository<Client, Integer>{

}
