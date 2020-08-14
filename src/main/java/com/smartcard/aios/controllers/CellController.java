package com.smartcard.aios.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartcard.aios.models.Cell;
import com.smartcard.aios.models.Sector;
import com.smartcard.aios.services.CellService;
import com.smartcard.aios.services.SectorService;



@Controller
public class CellController {

	@Autowired
	private CellService cellService;
	
	@Autowired
	private SectorService sectorService;
	
	
	
	
	@GetMapping("/cells")
	public String getCells(Model model) {
		
		List<Cell> cellList = cellService.getCells();
		model.addAttribute("cells", cellList);
		
		List<Sector> sectorList = sectorService.getSectors();
		model.addAttribute("sectors", sectorList);
		
		
		
		return "cell";
	}
	
	@PostMapping("/cells/addNew")
	public String addNew(Cell cell) {
	    cellService.save(cell);
	    return "redirect:/cells";
	}
	
	@RequestMapping("/cells/findById")
	@ResponseBody
	public Optional<Cell> findById(Integer id) {
		return cellService.findById(id);
	}
	
	@RequestMapping(value = "/cells/update", method= {RequestMethod.PUT,RequestMethod.GET})
	public String update(Cell cell) {
	    cellService.update(cell);
	    return "redirect:/cells";
	}
	
	
	@RequestMapping(value = "/cells/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	public String delete(Integer id) {
	    cellService.delete(id);
	    return "redirect:/cells";
	}
	
	
}
