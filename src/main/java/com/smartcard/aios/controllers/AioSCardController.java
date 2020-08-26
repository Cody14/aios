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

import com.google.zxing.WriterException;
import com.smartcard.aios.models.AioSCard;
import com.smartcard.aios.models.Citizen;
import com.smartcard.aios.models.District;
import com.smartcard.aios.models.DrivingLicense;
import com.smartcard.aios.models.HealthInsurance;
import com.smartcard.aios.models.NationalId;
import com.smartcard.aios.models.Sector;
import com.smartcard.aios.models.Village;
import com.smartcard.aios.repositories.NationalIdRepository;
import com.smartcard.aios.services.AioSCardService;
import com.smartcard.aios.services.CitizenService;
import com.smartcard.aios.services.DistrictService;
import com.smartcard.aios.services.DrivingLicenseService;
import com.smartcard.aios.services.HealthInsuranceService;
import com.smartcard.aios.services.NationalIdService;
import com.smartcard.aios.services.SectorService;
import com.smartcard.aios.services.VillageService;

import lombok.extern.java.Log;

@Controller
public class AioSCardController {

	@Autowired
	private AioSCardService aioSCardService;
	
	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private CitizenService citizenService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private VillageService villageService;
	
	@Autowired
	private NationalIdService nationalIdService;
	
	@Autowired
	private DrivingLicenseService drivingLicenseService;
	
	@Autowired
	private HealthInsuranceService healthInsuranceService;

	
	@GetMapping("/aioSCards")
	public String getAioSCards(Model model,String keyword) {
		
		if(keyword!=null) {
			model.addAttribute("aioSCards", aioSCardService.findByKeyword(keyword));
		}else{
//			List<AioSCard> aioSCardList = aioSCardService.getAioSCards();
//			model.addAttribute("aioSCards", aioSCardList);
		}
		List<Sector> sectorList = sectorService.getSectors();
		model.addAttribute("sectors", sectorList);
		
		return "aioSCard";
	}
	
	
	@GetMapping("/aiosCardPrint")
	public String getPrintAiosCard(Model model,String keyword) {
		
		if(keyword!=null) {
			model.addAttribute("aioSCards", aioSCardService.findByKeyword(keyword));
		}else{
//			List<AioSCard> aioSCardList = aioSCardService.getAioSCards();
//			model.addAttribute("aioSCards", aioSCardList);
		}
		List<Sector> sectorList = sectorService.getSectors();
		model.addAttribute("sectors", sectorList);
		
		//return "print";
		
		return "printAIO";
	}
	
	
	
	
	
	
	@GetMapping("/aioSCardCitizenInfos")
	public String getCitizenInfos(Model model,String keyword) {
		if(keyword!=null) {
			model.addAttribute("citizens", citizenService.findByKeyword(keyword));
			model.addAttribute("aioSCards", aioSCardService.findByKeyword(keyword));
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
		
		return "aioSCard";
	}
	
	
	@GetMapping("/aioSCardCitizenInfosToPrint")
	public String getAioSmartCardInfosToPrint(Model model,String keyword) {
		if(keyword!=null) {
			model.addAttribute("citizens", citizenService.findByKeyword(keyword));
			model.addAttribute("aioSCards", aioSCardService.findByKeyword(keyword));
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
		
		return "printAIO";
	}
	
	
	
	@GetMapping("/LinkNidAndAio")
	public String getNidAndAioSCard(Model model,String keyword) {
		if(keyword!=null) {
			model.addAttribute("citizens", citizenService.findByKeyword(keyword));
			model.addAttribute("aioSCards", aioSCardService.findByKeyword(keyword));
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
		
		return "linknid";
	}
	
	@GetMapping("/LinkDlAndAio")
	public String getDlAndAioSCard(Model model,String keyword) {
		if(keyword!=null) {
			model.addAttribute("citizens", citizenService.findByKeyword(keyword));
			model.addAttribute("aioSCards", aioSCardService.findByKeyword(keyword));
			model.addAttribute("drivingLicenses", drivingLicenseService.findByKeyword(keyword));
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
		
		return "linkdl";
	}
	
	
	@GetMapping("/LinkHiAndAio")
	public String getHiAndAioSCard(Model model,String keyword) {
		if(keyword!=null) {
			model.addAttribute("citizens", citizenService.findByKeyword(keyword));
			model.addAttribute("aioSCards", aioSCardService.findByKeyword(keyword));
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
		
		return "linkhi";
	}
	
	
	
	
	
	@PostMapping("/aioSCards/addNew")
	public String addNew(AioSCard aioSCard) throws WriterException, IOException {
	    aioSCardService.save(aioSCard);
	    return "redirect:/aioSCards";
	}
	
	@RequestMapping("/aioSCards/findById")
	@ResponseBody
	public Optional<AioSCard> findById(Integer id) {
		return aioSCardService.findById(id);
	}
	
	@RequestMapping(value = "/aioSCards/update", method= {RequestMethod.PUT,RequestMethod.GET})
	public String update(AioSCard aioSCard) {
		aioSCardService.update(aioSCard);
	    return "redirect:/aioSCards";
	}
	
	@RequestMapping(value = "/linkAioSCardWithNid", method= {RequestMethod.PUT,RequestMethod.GET})
	public String linkNid(AioSCard aioSCard,String keyword) throws WriterException, IOException {
		
		aioSCard = aioSCardService.getAioSCardByKeyword(keyword);
		
		NationalId nationalId = nationalIdService.getNationalId(aioSCard.getCitizenUsername());
		
		aioSCard.setNationalId(nationalId);
		
		aioSCardService.linkNidService(aioSCard, keyword);
		return "linknid";
	}
	
	@RequestMapping(value = "/linkAioSCardWithDl", method= {RequestMethod.PUT,RequestMethod.GET})
	public String linkDl(AioSCard aioSCard,String keyword) throws WriterException, IOException {
		
		aioSCard = aioSCardService.getAioSCardByKeyword(keyword);
		DrivingLicense drivingLicense = drivingLicenseService.getDrivingLicense(aioSCard.getCitizenUsername());
		
		aioSCard.setDrivingLicense(drivingLicense);
		
		aioSCardService.linkDlService(aioSCard, keyword);
		return "linkdl";
	}
	
	@RequestMapping(value = "/linkAioSCardWithHi", method= {RequestMethod.PUT,RequestMethod.GET})
	public String linkHi(AioSCard aioSCard,String keyword) throws WriterException, IOException {
		
		aioSCard = aioSCardService.getAioSCardByKeyword(keyword);

		HealthInsurance healthInsurance = healthInsuranceService.getHealthInsurance(aioSCard.getCitizenUsername());
		
		aioSCard.setHealthInsurance(healthInsurance);

		aioSCardService.linkHiService(aioSCard, keyword);
		return "linkhi";
	}
	
	
	@RequestMapping(value = "/aioSCards/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	public String delete(Integer id) {
	    aioSCardService.delete(id);
	    return "redirect:/aioSCards";
	}
	
	
}
