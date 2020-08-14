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
import com.smartcard.aios.models.Province;
import com.smartcard.aios.services.DistrictService;
import com.smartcard.aios.services.ProvinceService;





@Controller
public class DistrictController {

	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private ProvinceService provinceService;
	
	
	
	
	@GetMapping("/districts")
	public String getDistricts(Model model) {
		
		List<District> districtList = districtService.getDistricts();
		model.addAttribute("districts", districtList);
		
		List<Province> provinceList = provinceService.getProvinces();
		model.addAttribute("provinces", provinceList);
		
		
		
		return "district";
	}
	
	@PostMapping("/districts/addNew")
	public String addNew(District district) {
	    districtService.save(district);
	    return "redirect:/districts";
	}
	
	@RequestMapping("/districts/findById")
	@ResponseBody
	public Optional<District> findById(Integer id) {
		return districtService.findById(id);
	}
	
	@RequestMapping(value = "/districts/update", method= {RequestMethod.PUT,RequestMethod.GET})
	public String update(District district) {
	    districtService.update(district);
	    return "redirect:/districts";
	}
	
	
	@RequestMapping(value = "/districts/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	public String delete(Integer id) {
	    districtService.delete(id);
	    return "redirect:/districts";
	}
	
	
}
