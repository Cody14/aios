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
import com.smartcard.aios.models.HealthInsurance;
import com.smartcard.aios.models.Sector;
import com.smartcard.aios.models.Village;
import com.smartcard.aios.services.CitizenService;
import com.smartcard.aios.services.DistrictService;
import com.smartcard.aios.services.HealthInsuranceService;
import com.smartcard.aios.services.NationalIdService;
import com.smartcard.aios.services.SectorService;
import com.smartcard.aios.services.VillageService;




@Controller
public class HealthInsuranceController {

	@Autowired
	private HealthInsuranceService healthInsuranceService;
	
	@Autowired
	private NationalIdService nationalIdService;
	
	@Autowired
	private SectorService sectorService;
	
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private VillageService villageService;
	
	@Autowired
	private CitizenService citizenService;
	
	@GetMapping("/healthInsurances")
	public String getHealthInsurances(Model model,String keyword) {
		
		if(keyword!=null) {
			model.addAttribute("healthInsurances", healthInsuranceService.findByKeyword(keyword));

		}else {
			//List<HealthInsurance> healthInsuranceList = healthInsuranceService.getHealthInsurances();
			//model.addAttribute("healthInsurances", healthInsuranceList);
		}
		
		
		
		
		return "healthInsurance";
	}
	
	
	
	@GetMapping("/healthInsuranceCitizenInfos")
	public String getCitizenInfos(Model model,String keyword) {
		if(keyword!=null) {
			model.addAttribute("citizens", citizenService.findByKeyword(keyword));
			model.addAttribute("healthInsurances", healthInsuranceService.findByKeyword(keyword));
			List<Sector> sectorList = sectorService.getSectors();
			model.addAttribute("sectors", sectorList);
		}else {
			
			//List<Citizen> citizenList = citizenService.getCitizens();
			//model.addAttribute("citizens", citizenList);
		}
		
		List<District> districtList = districtService.getDistricts();
		model.addAttribute("districts", districtList);
		
		List<Village> villageList = villageService.getVillages();
		model.addAttribute("villages", villageList);
		
		List<Sector> sectorList = sectorService.getSectors();
		model.addAttribute("sectors", sectorList);
		
		return "healthInsurance";
	}
	
	
	
	@PostMapping("/healthInsurances/addNew")
	public String addNew(HealthInsurance healthInsurance) {
	    healthInsuranceService.save(healthInsurance);
	    return "redirect:/healthInsurances";
	}
	
	@RequestMapping("/healthInsurances/findById")
	@ResponseBody
	public Optional<HealthInsurance> findById(Integer id) {
		return healthInsuranceService.findById(id);
	}
	
	@RequestMapping(value = "/healthInsurances/update", method= {RequestMethod.PUT,RequestMethod.GET})
	public String update(HealthInsurance healthInsurance) {
		
		HealthInsurance hl = healthInsuranceService.getHealthInsurance(healthInsurance.getCitizenUsername());
		
		hl.setAffiliateName(healthInsurance.getAffiliateName());
		
	    healthInsuranceService.update(hl);
	    return "redirect:/healthInsurances";
	}
	
	
	@RequestMapping(value = "/healthInsurances/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	public String delete(Integer id) {
	    healthInsuranceService.delete(id);
	    return "redirect:/healthInsurances";
	}
	
	
}
