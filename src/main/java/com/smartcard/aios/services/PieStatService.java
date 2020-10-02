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

import com.smartcard.aios.models.PieStat;
import com.smartcard.aios.models.User;
import com.smartcard.aios.repositories.CitizenRepository;

import com.smartcard.aios.repositories.PieStatRepository;
import com.smartcard.aios.repositories.UserRepository;





@Service
public class PieStatService {

    
	
	@Autowired
	PieStatRepository pieStatRepository;
	
	
	public List<PieStat> getPieStats(){
		return pieStatRepository.findAll();
	}
		
	
		
}
