package fr.eni.contacts.ihm;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.contacts.bll.client.ClientManager;
import fr.eni.contacts.bo.Client;


@Controller
public class ClientController {

	@Autowired
	ClientManager manager;

	@GetMapping("/client/create")
	public String showCreateForm(Client client) {
//		client.setName("name");
		return "addClient";
	}

	@PostMapping("/client/add")
	public String addClient(@Valid Client client, BindingResult result, Model model){
		if (result.hasErrors()) {
			return "addClient";
		}
		manager.add(client);
		return "redirect:/client/index";

	}

	@GetMapping("/client/index")
	public String listClients(Model model) {
		model.addAttribute("clients", manager.getAll());
		return "indexClient";
	}

	@GetMapping("client/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Client client = manager.getById(id);
		model.addAttribute("client", client);
		return "updateClient";
	}

	@PostMapping("client/update/{id}")
	public String updateClient(@PathVariable("id") Integer id, @Valid Client client, BindingResult result,
			Model model) {
		client.setId(id);
		if (result.hasErrors()) {
			return "updateClient";
		}
		manager.update(client);
		return "redirect:/client/index";
	}
	
	@GetMapping("/client/delete/{id}")
	public String deleteClient(@PathVariable("id") Integer id, Model model) {	
		manager.removeFromId(id);
		
	    return "redirect:/client/index";
	}

}
