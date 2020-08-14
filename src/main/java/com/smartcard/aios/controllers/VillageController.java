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
import com.smartcard.aios.models.Village;
import com.smartcard.aios.services.CellService;
import com.smartcard.aios.services.VillageService;





@Controller
public class VillageController {

	@Autowired
	private VillageService villageService;
	
	@Autowired
	private CellService cellService;
	
	
	
	
	@GetMapping("/villages")
	public String getVillages(Model model) {
		
		List<Village> villageList = villageService.getVillages();
		model.addAttribute("villages", villageList);
		
		List<Cell> cellList = cellService.getCells();
		model.addAttribute("cells", cellList);

		return "village";
	}
	
	@PostMapping("/villages/addNew")
	public String addNew(Village village) {
	    villageService.save(village);
	    return "redirect:/villages";
	}
	
	@RequestMapping("/villages/findById")
	@ResponseBody
	public Optional<Village> findById(Integer id) {
		return villageService.findById(id);
	}
	
	@RequestMapping(value = "/villages/update", method= {RequestMethod.PUT,RequestMethod.GET})
	public String update(Village village) {
	    villageService.update(village);
	    return "redirect:/villages";
	}
	
	
	@RequestMapping(value = "/villages/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	public String delete(Integer id) {
	    villageService.delete(id);
	    return "redirect:/villages";
	}
	
	
}
