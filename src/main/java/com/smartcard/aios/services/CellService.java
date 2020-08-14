package com.smartcard.aios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcard.aios.models.Cell;
import com.smartcard.aios.repositories.CellRepository;




@Service
public class CellService {

	@Autowired
	private CellRepository cellRepository;
	
	//return List of all cells
		public List<Cell> getCells(){
			return cellRepository.findAll();
		}
		
		// save a new cell
		public void save(Cell cell) {
			cellRepository.save(cell);
		}
		
		// find a cell by id
		
		public Optional<Cell> findById (Integer id) {
			return cellRepository.findById(id);

		}
		
		public void update(Cell cell) {
			cellRepository.save(cell);
		}
		
		public void delete(Integer id) {
			cellRepository.deleteById(id);
		}
}
