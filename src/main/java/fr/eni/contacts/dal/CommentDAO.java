package fr.eni.contacts.dal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.eni.contacts.bo.Comment;
import fr.eni.contacts.bo.Contact;

public interface CommentDAO extends CrudRepository<Comment, Integer>{
	public List<Comment> findByContact(Contact contact);
}
