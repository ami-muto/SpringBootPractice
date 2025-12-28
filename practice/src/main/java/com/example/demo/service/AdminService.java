package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;
import com.example.demo.form.SigninForm;

public interface AdminService {
	void signup(AdminForm  form);
	
	Optional<Admin> signin(SigninForm form);
}
	