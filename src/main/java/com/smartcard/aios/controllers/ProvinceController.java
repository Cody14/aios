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

import com.smartcard.aios.models.Province;
import com.smartcard.aios.services.ProvinceService;





@Controller
public class ProvinceController {

	@Autowired
	private ProvinceService provinceService;
	
	@GetMapping("/provinces")
	public String getProvinces(Model model) {
		
		List<Province> provinceList = provinceService.getProvinces();
		model.addAttribute("provinces", provinceList);
		return "province";
	}
	
	@PostMapping("/provinces/addNew")
	public String addNew(Province province) {
	    provinceService.save(province);
	    return "redirect:/provinces";
	}
	
	@RequestMapping("/provinces/findById")
	@ResponseBody
	public Optional<Province> findById(Integer id) {
		return provinceService.findById(id);
	}
	
	@RequestMapping(value = "/provinces/update", method= {RequestMethod.PUT,RequestMethod.GET})
	public String update(Province province) {
	    provinceService.update(province);
	    return "redirect:/provinces";
	}
	
	
	@RequestMapping(value = "/provinces/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	public String delete(Integer id) {
	    provinceService.delete(id);
	    return "redirect:/provinces";
	}
	
	
}
