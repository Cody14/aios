package com.smartcard.aios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcard.aios.models.Village;
import com.smartcard.aios.repositories.VillageRepository;




@Service
public class VillageService {

	@Autowired
	private VillageRepository villageRepository;
	
	//return List of all villages
		public List<Village> getVillages(){
			return villageRepository.findAll();
		}
		
		// save a new village
		public void save(Village village) {
			villageRepository.save(village);
		}
		
		// find a village by id
		
		public Optional<Village> findById (Integer id) {
			return villageRepository.findById(id);

		}
		
		public void update(Village village) {
			villageRepository.save(village);
		}
		
		public void delete(Integer id) {
			villageRepository.deleteById(id);
		}
}
