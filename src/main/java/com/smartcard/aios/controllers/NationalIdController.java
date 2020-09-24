package com.smartcard.aios.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.smartcard.aios.models.Citizen;
import com.smartcard.aios.models.District;
import com.smartcard.aios.models.NationalId;
import com.smartcard.aios.models.Sector;
import com.smartcard.aios.models.Village;
import com.smartcard.aios.services.CitizenService;
import com.smartcard.aios.services.DistrictService;
import com.smartcard.aios.services.NationalIdService;
import com.smartcard.aios.services.SectorService;
import com.smartcard.aios.services.VillageService;

import lombok.extern.java.Log;

@Controller
public class NationalIdController {

	@Autowired
	private NationalIdService nationalIdService;
	
	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private CitizenService citizenService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private VillageService villageService;

	
	@GetMapping("/nationalIds")
	public String getNationalIds(Model model,String keyword) {
		
		if(keyword!=null) {
			model.addAttribute("nationalIds", nationalIdService.findByKeyword(keyword));
		}else{
//			List<NationalId> nationalIdList = nationalIdService.getNationalIds();
//			model.addAttribute("nationalIds", nationalIdList);
		}
		List<Sector> sectorList = sectorService.getSectors();
		model.addAttribute("sectors", sectorList);
		
		return "nationalId";
	}
	

	
	@GetMapping("/dnationalIds")
	public String getDNationalIds(Model model) {
		List<NationalId> activeNidList = nationalIdService.activeNidsList();
		model.addAttribute("nationalIds", activeNidList);
		return "dnationalId";
	}
	
	@GetMapping("/anationalIds")
	public String getInactiveDNationalIds(Model model) {
		List<NationalId> inactiveNidList = nationalIdService.inactiveNidsList();
		model.addAttribute("nationalIds", inactiveNidList);
		return "anationalId";
	}
	
	
	@GetMapping("/unationalIds")
	public String getUNationalIds(Model model) {
		List<NationalId> activeNidList = nationalIdService.linkedNidsList();
		model.addAttribute("nationalIds", activeNidList);
		return "unationalId";
	}
	
	
	
	@GetMapping("/nationalIdCitizenInfos")
	public String getCitizenInfos(Model model,String keyword) {
		if(keyword!=null) {
			model.addAttribute("citizens", citizenService.findByKeyword(keyword));
			model.addAttribute("nationalIds", nationalIdService.findByKeyword(keyword));
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
		
		return "nationalId";
	}
	
	
	
	
	@PostMapping("/nationalIds/addNew")
	public String addNew(NationalId nationalId) {
	    nationalIdService.save(nationalId);
	    return "redirect:/nationalIds";
	}
	
	@RequestMapping("/nationalIds/findById")
	@ResponseBody
	public Optional<NationalId> findById(Integer id) {
		return nationalIdService.findById(id);
	}
	
	@RequestMapping(value = "/nationalIds/update", method= {RequestMethod.PUT,RequestMethod.GET})
	public String update(NationalId nationalId) {
	   try {
	    NationalId nid = nationalIdService.getNationalId(nationalId.getCitizenUsername());   
		nid.setPlaceIssueId(nationalId.getPlaceIssueId());
		nationalIdService.update(nid);   
   
	} catch (Exception e) {
		return "error update"+e;
	}
		
		

	    return "redirect:/nationalIds";
	}
	
	
	@RequestMapping(value = "/nationalIds/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	public String delete(Integer id) {
	    nationalIdService.delete(id);
	    return "redirect:/nationalIds";
	}
	
	
	
	
	
	
}
