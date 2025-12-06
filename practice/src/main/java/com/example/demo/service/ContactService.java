package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.form.EditContactForm;

public interface ContactService {
	
	void saveContact(ContactForm contactForm);
	
	//一覧取得メソッドを追加
	List<Contact> getAllContacts();
	Contact getContactById(Long id);
	
	//編集（更新）
	void updateContact(Long id, EditContactForm editcontactForm);
	
	//削除
	void deleteContact(Long id);
}
