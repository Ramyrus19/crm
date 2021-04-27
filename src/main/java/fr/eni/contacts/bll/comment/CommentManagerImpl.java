package fr.eni.contacts.bll.comment;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.contacts.bo.Comment;
import fr.eni.contacts.bo.Contact;
import fr.eni.contacts.dal.CommentDAO;
import fr.eni.contacts.dal.ContactDAO;

@Service
public class CommentManagerImpl implements CommentManager {
	
	@Autowired
	private CommentDAO dao;
	@Autowired
	private ContactDAO daoContact;
	
	@Override
	@Transactional
	public void add(Comment comment, Contact contact) {
		Contact c = daoContact.findById(contact.getId()).get();
		comment.setContact(c);
		dao.save(comment);
	}

	@Override
	@Transactional
	public void update(Comment comment) {
		dao.save(comment);
	}

	@Override
	@Transactional
	public void delete(Comment comment) {
		dao.delete(comment);
	}

	@Override
	public List<Comment> getAll() {
		List<Comment> list = new ArrayList<>();
		for (Comment comment : dao.findAll()) {
			list.add(comment);
		}
		return list;
	}

	@Override
	public List<Comment> getByContact(Contact contact) {
		return dao.findByContact(contact);
	}

}
