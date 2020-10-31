package com.smartcard.aios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcard.aios.models.AdminMessage;

import com.smartcard.aios.repositories.AdminMessageRepository;





@Service
public class AdminMessageService {

	@Autowired
	private AdminMessageRepository adminMessageRepository;
	
	//return List of all adminMessages
		public List<AdminMessage> getAdminMessages(){
			return adminMessageRepository.readAdminMessages();
		}
		
		
		public AdminMessage getAdminMessage(String username) {
			return adminMessageRepository.findByCitizenUsername(username);
		}
		
		
		// save a new adminMessage
		public void save(AdminMessage adminMessage) {
			adminMessageRepository.save(adminMessage);
		}
		
		// find a adminMessage by id
		
		public Optional<AdminMessage> findById (Integer id) {
			return adminMessageRepository.findById(id);

		}
		
		public void update(AdminMessage adminMessage) {
			adminMessageRepository.save(adminMessage);
		}
		
		public void delete(Integer id) {
			adminMessageRepository.deleteById(id);
		}
		
		public void seenMessage(String username) {
			AdminMessage am = adminMessageRepository.findByCitizenUsername(username);
			am.setMessageStatus("seen");
			adminMessageRepository.save(am);
		}
}
