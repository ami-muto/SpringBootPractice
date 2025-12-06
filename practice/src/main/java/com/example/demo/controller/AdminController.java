package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Contact;
import com.example.demo.form.EditContactForm;
import com.example.demo.service.ContactService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private ContactService contactService;

	@GetMapping("/contacts")
	public String contactList(Model model) {
		List<Contact> contactList = contactService.getAllContacts();
		model.addAttribute("contacts", contactList);
		return "contactList";
	}

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}

	@GetMapping("/contacts/{id}")
	public String detail(@PathVariable Long id, Model model) {
		Contact contact = contactService.getContactById(id);
		model.addAttribute("contact", contact);
		return "detail";
	}
	
	@GetMapping("/contacts/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		Contact contact = contactService.getContactById(id);
		
		EditContactForm editcontactform = new EditContactForm();
		editcontactform.setLastName(contact.getLastName());
		editcontactform.setFirstName(contact.getFirstName());
		editcontactform.setEmail(contact.getEmail());
		editcontactform.setPhone(contact.getPhone());
		editcontactform.setZipCode(contact.getZipCode());
		editcontactform.setAddress(contact.getAddress());
		editcontactform.setBuildingName(contact.getBuildingName());
		editcontactform.setContactType(contact.getContactType());
		editcontactform.setBody(contact.getBody());
		
		model.addAttribute("editContactForm", editcontactform);
		model.addAttribute("contactId", contact.getId());
		return "edit";
	}
	
	@PostMapping("/contacts/{id}/edit")
	public String update(@PathVariable Long id, @ModelAttribute EditContactForm editContactForm) {
		contactService.updateContact(id, editContactForm);
		return"redirect:/admin/contacts";
	}
	@PostMapping("/contacts/{id}")
	public String delete(@PathVariable Long id) {
		contactService.deleteContact(id);
		return"redirect:/admin/contacts";
	}
	
	@PostMapping("/logout")
	public String logout() {
	return"redirect:/signin";
	}
}
