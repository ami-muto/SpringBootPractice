package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;
import com.example.demo.form.SigninForm;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void signup(AdminForm form) {
        if (adminRepository.existsByEmail(form.getEmail())) {
            throw new RuntimeException("Duplicate entry for email");
        }
        
        Admin admin = new Admin();
        admin.setLastName(form.getLastName());
        admin.setFirstName(form.getFirstName());
        admin.setEmail(form.getEmail());
        admin.setPassword(passwordEncoder.encode(form.getPassword()));  
        
        adminRepository.save(admin);
    }
    
    @Override
    public Optional<Admin> signin(SigninForm form){
        Admin admin = adminRepository.findByEmail(form.getEmail());
        if (admin == null) {
            return Optional.empty();
        }
        
        if (passwordEncoder.matches(form.getPassword(), admin.getPassword())) {
            return Optional.of(admin);
        }
        
        return Optional.empty();
    }
}

