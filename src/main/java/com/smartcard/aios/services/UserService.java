package com.smartcard.aios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartcard.aios.models.User;
import com.smartcard.aios.repositories.UserRepository;





@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	//return List of all users
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	public User findByUsername(String username) {
	   return userRepository.findByUsername(username);
	}
	
	// save a new user
	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	// find a user by id
	
	public Optional<User> findById (Integer id) {
		return userRepository.findById(id);

	}
	
	public void update(User user) {
		userRepository.save(user);
	}
	
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}
}
