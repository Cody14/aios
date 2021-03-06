package com.smartcard.aios.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.smartcard.aios.models.District;
import com.smartcard.aios.models.NationalId;
import com.smartcard.aios.models.Sector;
import com.smartcard.aios.models.Village;
import com.smartcard.aios.services.CitizenService;
import com.smartcard.aios.services.DistrictService;
import com.smartcard.aios.services.NationalIdService;
import com.smartcard.aios.services.SectorService;
import com.smartcard.aios.services.VillageService;


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
	
	@Autowired
	private ApplicationContext applicationContext;

	
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
	public RedirectView addNew(NationalId nationalId,RedirectAttributes redir) {
		
		try {
			 nationalIdService.save(nationalId);
			 RedirectView  redirectView = new RedirectView("/nationalIds",true);
			 redir.addFlashAttribute("reqM","National ID Generated Successfuly!");
			 return redirectView;
			
		} catch (Exception e) {
			
			 RedirectView  redirectView = new RedirectView("/nationalIds",true);
			 redir.addFlashAttribute("reqMe","National ID Failed To be Generated");
			 return redirectView;
		}
		
		
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
