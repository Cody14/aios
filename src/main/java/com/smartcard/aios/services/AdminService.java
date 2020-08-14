package com.smartcard.aios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcard.aios.models.Admin;
import com.smartcard.aios.repositories.AdminRepository;




@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	
	
	//return List of all admin
		public List<Admin> getAdmins(){
			return adminRepository.findAll();
		}
		
		// save a new admin
		public void save(Admin admin) {
			adminRepository.save(admin);
		}
		
		// find a admin by id
		
		public Optional<Admin> findById (Integer id) {
			return adminRepository.findById(id);

		}
		
		public void update(Admin admin) {
			adminRepository.save(admin);
		}
		
		public void delete(Integer id) {
			adminRepository.deleteById(id);
		}
}
