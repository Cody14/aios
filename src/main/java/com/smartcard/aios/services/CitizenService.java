package com.smartcard.aios.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smartcard.aios.models.Citizen;
import com.smartcard.aios.models.User;
import com.smartcard.aios.repositories.CitizenRepository;
import com.smartcard.aios.repositories.UserRepository;





@Service
public class CitizenService {

    
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	@Autowired
	private UserService userService;
	
	
	//return List of all citizens
		public List<Citizen> getCitizens(){
			return citizenRepository.findAll();
		}
		
		public Citizen getCitizen(String username) {
			return citizenRepository.findAllByUsername(username);
		}
		
		public List<Citizen> allPendingList(String status){
			status="Pending";
			
			return citizenRepository.pendingList(status);
		}
		
		// save a new citizen
		public void save(Citizen citizen,@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
			
			String baseDir = "C:\\Users\\Theophile\\Desktop\\aiosmartcard\\aios\\aios\\src\\main\\resources\\static\\uploads\\";
			

			
			User user = userService.findByUsername(citizen.getUsername());
			file.transferTo(new File(baseDir + user.getUsername()+".jpg"));
			
			citizen.setPhoto(baseDir + user.getUsername()+".jpg");
			
			
			
			 user = userRepository.findByUsername(citizen.getUsername());
			citizen.setUser(user);
			citizenRepository.save(citizen);
			
			
		}
		
		// find a citizen by id
		
		public Optional<Citizen> findById (Integer id) {
			return citizenRepository.findById(id);

		}
		
		public void  update( Citizen citizen) {
	
			citizenRepository.save(citizen);
		}
		
	
		public Citizen rejectCitizen(String username) {
			
			Citizen c = citizenRepository.findAllByUsername("Gasana");
			
			c.setFormRequest("Rejected");
			
			return citizenRepository.findByUsername(username);
		}
		
		
		public void delete(Integer id) {
			citizenRepository.deleteById(id);
		}
		
		
		public void reject(Integer id,String status) {
			
			status ="Rejected";
			citizenRepository.reject(id,status);
			
		}
		
        public void accept(Integer id,String status,String passKey) {
			
        	    int pkNo = 0;
			   
        	    pkNo = (int)((Math.random() * 9000)+1000);
			
        	
        	
        	passKey = "Ctzn "+pkNo;
			status ="Accepted";
			citizenRepository.accept(id,status,passKey);
			
		}
		
		
        
		
		// Get citizen by keyword
		
		public List<Citizen> findByKeyword(String keyword){
			return citizenRepository.findByKeyowrd(keyword);
		}
		
	
		
}
