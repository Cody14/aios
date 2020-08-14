package com.smartcard.aios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcard.aios.models.District;
import com.smartcard.aios.repositories.DistrictRepository;



@Service
public class DistrictService {

	@Autowired
	private DistrictRepository districtRepository;
	
	//return List of all districts
		public List<District> getDistricts(){
			return districtRepository.findAll();
		}
		
		// save a new district
		public void save(District district) {
			districtRepository.save(district);
		}
		
		// find a district by id
		
		public Optional<District> findById (Integer id) {
			return districtRepository.findById(id);

		}
		
		public void update(District district) {
			districtRepository.save(district);
		}
		
		public void delete(Integer id) {
			districtRepository.deleteById(id);
		}
}
