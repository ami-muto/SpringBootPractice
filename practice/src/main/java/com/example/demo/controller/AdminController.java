package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Contact;
import com.example.demo.form.AdminForm;
import com.example.demo.form.EditContactForm;
import com.example.demo.service.AdminService;
import com.example.demo.service.ContactService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private AdminService adminService;

	@GetMapping("/contacts")
	public String contactList(Model model) {
		model.addAttribute("contacts", contactService.getAllContacts());
		return "contactList";
	}

	@GetMapping("/signup")
	public String signupForm(Model model) {
		model.addAttribute("adminForm", new AdminForm());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute AdminForm form) {
		System.out.println("★Signupメソッドが呼ばれました！ メール: " + form.getEmail());
		adminService.signup(form);
		return "redirect:/admin/signin";
		}
		
	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}
	
	

	@GetMapping("/contacts/{id}")
	public String detail(@PathVariable Long id, Model model) {
		model.addAttribute("contact", contactService.getContactById(id));
		return "detail";
	}
	
	@GetMapping("/contacts/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		Contact contact = contactService.getContactById(id);
		
		EditContactForm form  = new EditContactForm();
		form.setLastName(contact.getLastName());
		form.setFirstName(contact.getFirstName());
		form.setEmail(contact.getEmail());
		form.setPhone(contact.getPhone());
		form.setZipCode(contact.getZipCode());
		form.setAddress(contact.getAddress());
		form.setBuildingName(contact.getBuildingName());
		form.setContactType(contact.getContactType());
		form.setBody(contact.getBody());
		
		model.addAttribute("editContactForm", form);
		model.addAttribute("contactId", id);
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
	
	
}
