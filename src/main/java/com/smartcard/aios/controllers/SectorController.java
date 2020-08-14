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

import com.smartcard.aios.models.District;
import com.smartcard.aios.models.Sector;
import com.smartcard.aios.services.DistrictService;
import com.smartcard.aios.services.SectorService;





@Controller
public class SectorController {

	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private DistrictService districtService;
	
	
	
	
	@GetMapping("/sectors")
	public String getSectors(Model model) {
		
		List<Sector> sectorList = sectorService.getSectors();
		model.addAttribute("sectors", sectorList);
		
		List<District> districtList = districtService.getDistricts();
		model.addAttribute("districts", districtList);
		
		
		
		return "sector";
	}
	
	@PostMapping("/sectors/addNew")
	public String addNew(Sector sector) {
	    sectorService.save(sector);
	    return "redirect:/sectors";
	}
	
	@RequestMapping("/sectors/findById")
	@ResponseBody
	public Optional<Sector> findById(Integer id) {
		return sectorService.findById(id);
	}
	
	@RequestMapping(value = "/sectors/update", method= {RequestMethod.PUT,RequestMethod.GET})
	public String update(Sector sector) {
	    sectorService.update(sector);
	    return "redirect:/sectors";
	}
	
	
	@RequestMapping(value = "/sectors/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	public String delete(Integer id) {
	    sectorService.delete(id);
	    return "redirect:/sectors";
	}
	
	
}
