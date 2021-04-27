package fr.eni.contacts.bll.comment;

import java.util.List;

import fr.eni.contacts.bo.Comment;
import fr.eni.contacts.bo.Contact;

/**
 * @author ramon
 * 
 * Interface to manage comments
 *
 */
public interface CommentManager {
	public void add(Comment comment, Contact contact);
	public void update(Comment comment);
	public void delete(Comment comment);
	public List<Comment> getAll();
	public List<Comment> getByContact(Contact contact);
	
}
