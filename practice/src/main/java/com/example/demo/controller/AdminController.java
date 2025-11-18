package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Contact;
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

}
