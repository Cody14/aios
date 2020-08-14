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

import com.smartcard.aios.models.Admin;
import com.smartcard.aios.models.Citizen;
import com.smartcard.aios.models.District;
import com.smartcard.aios.models.Village;
import com.smartcard.aios.services.AdminService;
import com.smartcard.aios.services.CitizenService;
import com.smartcard.aios.services.DistrictService;
import com.smartcard.aios.services.VillageService;



@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CitizenService citizenService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private VillageService villageService;
	
	
	@GetMapping("/admins")
	public String getAdmins(Model model) {
		
		List<Admin> adminList = adminService.getAdmins();
		model.addAttribute("admins", adminList);
		
		List<Citizen> citizenList = citizenService.getCitizens();
		model.addAttribute("citizens", citizenList);
		
		return "admin";
	}
	
	
	
	@GetMapping("/eForms")
	public String getCitizens(Model model, String keyword) {

          String status = "Pending";
         
		
		if(keyword!=null) {
			model.addAttribute("citizens", citizenService.findByKeyword(keyword));
		}else {
			
			List<Citizen> allPendingList = citizenService.allPendingList(status); // List of all pending form requests
			model.addAttribute("citizens", allPendingList);
		}
		
		List<District> districtList = districtService.getDistricts();
		model.addAttribute("districts", districtList);
		
		List<Village> villageList = villageService.getVillages();
		model.addAttribute("villages", villageList);
		
		return "eForms";
	}
	
	@PostMapping("/admins/addNew")
	public String addNew(Admin admin) {
	    adminService.save(admin);
	    return "redirect:/admins";
	}
	
	@RequestMapping("/admins/findById")
	@ResponseBody
	public Optional<Admin> findById(Integer id) {
		return adminService.findById(id);
	}
	
	@RequestMapping(value = "/admins/update", method= {RequestMethod.PUT,RequestMethod.GET})
	public String update(Admin admin) {
	    adminService.update(admin);
	    return "redirect:/admins";
	}
	
	
	@RequestMapping(value = "/admins/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	public String delete(Integer id) {
	    adminService.delete(id);
	    return "redirect:/admins";
	}
	
	
}
