package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.form.EditContactForm;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public void saveContact(ContactForm contactForm) {
		Contact contact = new Contact();

		contact.setLastName(contactForm.getLastName());
		contact.setFirstName(contactForm.getFirstName());
		contact.setEmail(contactForm.getEmail());
		contact.setPhone(contactForm.getPhone());
		contact.setZipCode(contactForm.getZipCode());
		contact.setAddress(contactForm.getAddress());
		contact.setBuildingName(contactForm.getBuildingName());
		contact.setContactType(contactForm.getContactType());
		contact.setBody(contactForm.getBody());

		contactRepository.save(contact);
	}

	@Override
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	@Override
	public Contact getContactById(Long id) {
		return contactRepository.findById(id)
				.orElseThrow(new java.util.function.Supplier<RuntimeException>() {
					@Override
					public RuntimeException get() {
						return new RuntimeException("Contact not found with id: " + id);
					}

				});
	}

	@Override
	public void updateContact(Long id, EditContactForm editcontactForm) {
		Contact contact = contactRepository.findById(id)
				.orElseThrow(new java.util.function.Supplier<RuntimeException>() {
					@Override
					public RuntimeException get() {
						return new RuntimeException("Contact not found with id: "+id);
					}
					
				});
		
		contact.setLastName(editcontactForm.getLastName());
		contact.setFirstName(editcontactForm.getFirstName());
		contact.setEmail(editcontactForm.getEmail());
		contact.setPhone(editcontactForm.getPhone());
		contact.setZipCode(editcontactForm.getZipCode());
		contact.setAddress(editcontactForm.getAddress());
		contact.setBuildingName(editcontactForm.getBuildingName());
		contact.setContactType(editcontactForm.getContactType());
		contact.setBody(editcontactForm.getBody());
		
		contactRepository.save(contact);
	}

	@Override
	public void deleteContact(Long id) {
		contactRepository.deleteById(id);
		// TODO 自動生成されたメソッド・スタブ
	}
}
