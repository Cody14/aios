package com.smartcard.aios.controllers;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.smartcard.aios.models.Citizen;
import com.smartcard.aios.models.District;
import com.smartcard.aios.models.Village;
import com.smartcard.aios.services.CitizenService;
import com.smartcard.aios.services.DistrictService;
import com.smartcard.aios.services.VillageService;




@Controller
public class CitizenController {

	@Autowired
	private CitizenService citizenService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private VillageService villageService;
	
	
	
	
	@GetMapping("/citizens")
	public String getCitizens(Model model, String keyword) {

		if(keyword!=null) {
			model.addAttribute("citizens", citizenService.findByKeyword(keyword));
		}else {
			
			//List<Citizen> citizenList = citizenService.getCitizens();
			//model.addAttribute("citizens", citizenList);
		}
		
		List<District> districtList = districtService.getDistricts();
		model.addAttribute("districts", districtList);
		
		List<Village> villageList = villageService.getVillages();
		model.addAttribute("villages", villageList);
		
		return "citizen";
	}
	
	
	
	
	
	
	
	@GetMapping("/eforms")
	public String getEForms(Model model, String keyword) {
         
         String status = "Pending";
         
         List<Citizen> allPendingList = citizenService.allPendingList(status); // List of all pending form requests
		 List<Citizen> citizenList = citizenService.getCitizens();

			if(keyword!=null) {
				model.addAttribute("citizens", citizenService.findByKeyword(keyword));
			}
			
			
			for(int i =0;i<citizenList.size();i++) {
				
			
			if(allPendingList.size()>=1) {
				
				
				
				List<Citizen> lastPending = allPendingList.subList(allPendingList.size()-1,allPendingList.size()); // Last element in all pending form requests
				
				model.addAttribute("citizens", lastPending);
				
			}
			
			else if(allPendingList.size()<1) {
				
				 model.addAttribute("citizens", allPendingList);
			}
			
			}
			
			List<District> districtList = districtService.getDistricts();
			model.addAttribute("districts", districtList);
			
			List<Village> villageList = villageService.getVillages();
			model.addAttribute("villages", villageList);
			return "eforms";
			
		} 
		
	
		
	
	
	
	
	
	@GetMapping("/citizensFiltered")
	public String getCitizensFiltered(Model model, String keyword) {

			
			List<Citizen> citizenList = citizenService.getCitizens();
			model.addAttribute("citizens", citizenList);
	
		
		List<District> districtList = districtService.getDistricts();
		model.addAttribute("districts", districtList);
		
		List<Village> villageList = villageService.getVillages();
		model.addAttribute("villages", villageList);
		
		return "citizen";
	}
	
	
	@PostMapping("/citizens/addNew")
	public RedirectView addNew(Citizen citizen,@RequestParam("file") MultipartFile file,RedirectAttributes redir) throws IllegalStateException, IOException {
	    
		try {
			citizenService.save(citizen, file);
			
			
			
		    RedirectView  redirectView = new RedirectView("/citizens",true);
		    redir.addFlashAttribute("reqM","Request Sent Successfully!");
		    return redirectView;
		} catch (Exception e) {
			
			 RedirectView  redirectView = new RedirectView("/citizens",true);
			 redir.addFlashAttribute("reqM","Form Failed To Submit!");
			return redirectView;
		}
		
		
	   // return "redirect:/citizens";
	}
	
	@RequestMapping("/citizens/findById")
	@ResponseBody
	public Optional<Citizen> findById(Integer id) {
		return citizenService.findById(id);
	}
	
	@RequestMapping("/citizens/findByName")
	@ResponseBody
	public Citizen findByName(String username) {
		return citizenService.getCitizen(username);
	}
	
	@RequestMapping(value = "/citizens/update", method= {RequestMethod.PUT,RequestMethod.GET})
	public String update(Citizen citizen) {
		
		try {
			Citizen c = citizenService.getCitizen(citizen.getUsername());
			c.setFirstname(citizen.getFirstname());
			c.setLastname(citizen.getLastname());
			c.setDateOfBirth(citizen.getDateOfBirth());
			c.setBirthPlaceId(citizen.getBirthPlaceId());
			c.setCategory(citizen.getCategory());
			c.setGender(citizen.getGender());
			c.setCurrentPlaceId(citizen.getCurrentPlaceId());
			c.setFatherName(citizen.getFatherName());
			c.setMotherName(citizen.getMotherName());
			c.setMartialStatus(citizen.getMartialStatus());
			c.setPhoto(citizen.getPhoto());
			 citizenService.update(c);
			 
		} catch (Exception e) {
			return "error update"+e;
		}
		return "redirect:/eforms";
		
	   
	}
	
	

	@RequestMapping(value ="/citizens/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	public String delete(Integer id) {	
	    citizenService.delete(id);
	    return "redirect:/eforms";
	}
	
	@RequestMapping(value ="/citizens/reject", method= {RequestMethod.PUT,RequestMethod.GET})
	public String reject(Integer id,String status) {			
		citizenService.reject(id,status);
		
	    return "redirect:/eForms";
	}
	
	@RequestMapping(value ="/citizens/accept", method= {RequestMethod.PUT,RequestMethod.GET})
	public String accept(Integer id,String status,String passKey) {	
		
		citizenService.accept(id,status,passKey);
		
	    return "redirect:/eForms";
	}
	
	
	
	
	
	
}
