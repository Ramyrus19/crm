package fr.eni.contacts.ihm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fr.eni.contacts.bll.client.ClientManager;


@Controller
public class ClientController {
	@Autowired
	ClientManager manager;
}
