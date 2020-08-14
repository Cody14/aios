package com.smartcard.aios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcard.aios.models.Province;
import com.smartcard.aios.repositories.ProvinceRepository;




@Service
public class ProvinceService {

	@Autowired
	private ProvinceRepository provinceRepository;
	
	//return List of all provinces
		public List<Province> getProvinces(){
			return provinceRepository.findAll();
		}
		
		// save a new province
		public void save(Province province) {
			provinceRepository.save(province);
		}
		
		// find a province by id
		
		public Optional<Province> findById (Integer id) {
			return provinceRepository.findById(id);

		}
		
		public void update(Province province) {
			provinceRepository.save(province);
		}
		
		public void delete(Integer id) {
			provinceRepository.deleteById(id);
		}
}
