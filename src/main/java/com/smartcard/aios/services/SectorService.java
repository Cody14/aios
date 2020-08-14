package com.smartcard.aios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcard.aios.models.Sector;
import com.smartcard.aios.repositories.SectorRepository;




@Service
public class SectorService {

	@Autowired
	private SectorRepository sectorRepository;
	
	//return List of all sectors
		public List<Sector> getSectors(){
			return sectorRepository.findAll();
		}
		
		// save a new sector
		public void save(Sector sector) {
			sectorRepository.save(sector);
		}
		
		// find a sector by id
		
		public Optional<Sector> findById (Integer id) {
			return sectorRepository.findById(id);

		}
		
		public void update(Sector sector) {
			sectorRepository.save(sector);
		}
		
		public void delete(Integer id) {
			sectorRepository.deleteById(id);
		}
}
