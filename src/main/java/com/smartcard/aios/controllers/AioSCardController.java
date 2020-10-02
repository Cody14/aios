package com.smartcard.aios.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.zxing.WriterException;
import com.smartcard.aios.models.AioSCard;
import com.smartcard.aios.models.Citizen;
import com.smartcard.aios.models.District;
import com.smartcard.aios.models.DrivingLicense;
import com.smartcard.aios.models.HealthInsurance;
import com.smartcard.aios.models.NationalId;
import com.smartcard.aios.models.PieStat;
import com.smartcard.aios.models.Sector;
import com.smartcard.aios.models.Village;
import com.smartcard.aios.repositories.NationalIdRepository;
import com.smartcard.aios.services.AioSCardService;
import com.smartcard.aios.services.CitizenService;
import com.smartcard.aios.services.DistrictService;
import com.smartcard.aios.services.DrivingLicenseService;
import com.smartcard.aios.services.HealthInsuranceService;
import com.smartcard.aios.services.NationalIdService;
import com.smartcard.aios.services.PieStatService;
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
	

	
	@Autowired
	PieStatService pieStatService;

	
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
	
	
	@RequestMapping("/chart")
	@ResponseBody
	public String chart() {
		List<Citizen> citizenList = citizenService.getCitizens();
		JsonArray jsonNames = new JsonArray();
		JsonArray jsonYears = new JsonArray();
		JsonObject json = new JsonObject();
	    citizenList.forEach(data->{
		jsonNames.add(data.getFirstname());
		jsonYears.add(data.getDateOfBirth().getYear());
	});	
	
	json.add("names", jsonNames);
	json.add("years", jsonYears);
	
	return json.toString(); 
	}
	
	
	
	@RequestMapping("/pieStat")
	@ResponseBody
	public ResponseEntity<?> pieChart(){		
		List<PieStat> listPieStats = pieStatService.getPieStats();
		return new ResponseEntity<>(listPieStats,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/aioAllReport")
	public String aioAllRepots(Model model) {
		List<AioSCard> activeAioList = aioSCardService.getAioSCards();
		model.addAttribute("aioSCards", activeAioList);
		return "aioReportAll";
	}
	
	
	@GetMapping("/nidAllReport")
	public String nidAllRepots(Model model) {
		List<NationalId> activeNidList = nationalIdService.activeNidsList();
		model.addAttribute("nationalIds", activeNidList);
		return "nidReportAll";
	}
	
	@GetMapping("/dlAllReport")
	public String dlAllRepots(Model model) {
		List<DrivingLicense> activeDlList = drivingLicenseService.activeDlList();
		model.addAttribute("drivingLicenses", activeDlList);
		return "dlReportAll";
	}
	
	@GetMapping("/hiAllReport")
	public String hiAllRepots(Model model,String keyword) {
		List<HealthInsurance> activeHiList = healthInsuranceService.getActiveHiList();
		model.addAttribute("healthInsurances", activeHiList);
		return "hiReportAll";
	}
	
	@GetMapping("/allreports")
	public String reports(Model model) {
		List<AioSCard> allAio = aioSCardService.getAioSCards();
		model.addAttribute("aioSCards", allAio);		
		return "reports";
	}
	
	
	@GetMapping("/daioscards")
	public String getAiosCards(Model model) {
		List<AioSCard> listaiocards = aioSCardService.getAioSCards();
		model.addAttribute("aiosCards", listaiocards);
		return "daioscard";
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
	
	
	
	// UNLINK NID
	@RequestMapping(value = "/unlinkAioSCardWithNid", method= {RequestMethod.PUT,RequestMethod.GET})
	public String unlinkNid1(AioSCard aioSCard,String keyword) throws WriterException, IOException {	
		aioSCard = aioSCardService.getAioSCardByKeyword(keyword);	
		aioSCard.setNationalId(null);   
		aioSCardService.UNLINKNid1Service(aioSCard, keyword);
		return "redirect:/unationalIds";
	}
	// END UNLINK NID
	
	
	// DISABLE NID
		@RequestMapping(value = "/disableAioSCardWithNid", method= {RequestMethod.PUT,RequestMethod.GET})
		public String disablekNid1(AioSCard aioSCard,String keyword) throws WriterException, IOException {	
			aioSCard = aioSCardService.getAioSCardByKeyword(keyword);
			NationalId nationalId = nationalIdService.getNationalId(aioSCard.getCitizenUsername());
			nationalId.setNidStatus("inactive");
			aioSCard.setNationalId(nationalId);   
			aioSCardService.disableNid1Service(aioSCard, keyword);
			return "redirect:/dnationalIds";
		}
		// END DISABLE NID
		
		
		// ACTIVE NID
				@RequestMapping(value = "/activeAioSCardWithNid", method= {RequestMethod.PUT,RequestMethod.GET})
				public String activekNid1(AioSCard aioSCard,String keyword) throws WriterException, IOException {	
					aioSCard = aioSCardService.getAioSCardByKeyword(keyword);
					NationalId nationalId = nationalIdService.getNationalId(aioSCard.getCitizenUsername());
					nationalId.setNidStatus("active");
					aioSCard.setNationalId(nationalId);   
					aioSCardService.activeNid1Service(aioSCard, keyword);
					return "redirect:/anationalIds";
				}
				// END ACTIVE NID
	
	
	
	
	
	@RequestMapping(value = "/linkAioSCardWithDl", method= {RequestMethod.PUT,RequestMethod.GET})
	public String linkDl(AioSCard aioSCard,String keyword) throws WriterException, IOException {
		
		aioSCard = aioSCardService.getAioSCardByKeyword(keyword);
		DrivingLicense drivingLicense = drivingLicenseService.getDrivingLicense(aioSCard.getCitizenUsername());
		
		aioSCard.setDrivingLicense(drivingLicense);
		
		aioSCardService.linkDlService(aioSCard, keyword);
		return "linkdl";
	}
	
	
	// UNLINK DL
	
	@RequestMapping(value = "/unlinkAioSCardWithDl", method= {RequestMethod.PUT,RequestMethod.GET})
	public String unlinkDl(AioSCard aioSCard,String keyword) throws WriterException, IOException {		
		aioSCard = aioSCardService.getAioSCardByKeyword(keyword);	
		aioSCard.setDrivingLicense(null);	
		aioSCardService.UNLINLDlService(aioSCard, keyword);
		return "redirect:/udrivingLicenses";
	}
	
	// END UNLINK DL
	
	
	// DISABLE DL
	
		@RequestMapping(value = "/disableAioSCardWithDl", method= {RequestMethod.PUT,RequestMethod.GET})
		public String disableDl(AioSCard aioSCard,String keyword) throws WriterException, IOException {		
			aioSCard = aioSCardService.getAioSCardByKeyword(keyword);
			DrivingLicense drivingLicense = drivingLicenseService.getDrivingLicense(aioSCard.getCitizenUsername());
			drivingLicense.setDlStatus("inactive");
			aioSCard.setDrivingLicense(drivingLicense);	
			aioSCardService.disableDlService(aioSCard, keyword);
			return "redirect:/ddrivingLicenses";
		}
		
		// END DISABLE DL
		
		
		// ACTIVE DL
		
			@RequestMapping(value = "/activeAioSCardWithDl", method= {RequestMethod.PUT,RequestMethod.GET})
			public String activeDl(AioSCard aioSCard,String keyword) throws WriterException, IOException {		
				aioSCard = aioSCardService.getAioSCardByKeyword(keyword);
				DrivingLicense drivingLicense = drivingLicenseService.getDrivingLicense(aioSCard.getCitizenUsername());
				drivingLicense.setDlStatus("active");
				aioSCard.setDrivingLicense(drivingLicense);	
				aioSCardService.activeDlService(aioSCard, keyword);
				return "redirect:/adrivingLicenses";
			}
			
			// END ACTIVE DL
	
	
	
	
	@RequestMapping(value = "/linkAioSCardWithHi", method= {RequestMethod.PUT,RequestMethod.GET})
	public String linkHi(AioSCard aioSCard,String keyword) throws WriterException, IOException {
		
		aioSCard = aioSCardService.getAioSCardByKeyword(keyword);

		HealthInsurance healthInsurance = healthInsuranceService.getHealthInsurance(aioSCard.getCitizenUsername());
		
		aioSCard.setHealthInsurance(healthInsurance);

		aioSCardService.linkHiService(aioSCard, keyword);
		return "linkhi";
	}
	
	// UNLINK HL
	
	@RequestMapping(value = "/unlinkAioSCardWithHi", method= {RequestMethod.PUT,RequestMethod.GET})
	public String unlinkHi(AioSCard aioSCard,String keyword) throws WriterException, IOException {
		aioSCard = aioSCardService.getAioSCardByKeyword(keyword);
		aioSCard.setHealthInsurance(null);
		aioSCardService.unlinkHiService(aioSCard, keyword);
		return "redirect:/uhealthInsurances";
	}
	
	
	// END UNLINK HL
	
	
	// DISABLE HL
	
		@RequestMapping(value = "/disableAioSCardWithHi", method= {RequestMethod.PUT,RequestMethod.GET})
		public String disableHi(AioSCard aioSCard,String keyword) throws WriterException, IOException {
			aioSCard = aioSCardService.getAioSCardByKeyword(keyword);
			HealthInsurance healthInsurance = healthInsuranceService.getHealthInsurance(aioSCard.getCitizenUsername());
			healthInsurance.setHi_status("inactive");
			aioSCard.setHealthInsurance(healthInsurance);
			aioSCardService.disableHiService(aioSCard, keyword);
			return "redirect:/dhealthInsurances";
		}
		
		
		// END UNLINK HL
	
	
	@RequestMapping(value = "/aioSCards/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	public String delete(Integer id) {
	    aioSCardService.delete(id);
	    return "redirect:/aioSCards";
	}
	
	@RequestMapping(value = "/aiosCard/unlinkNid", method = {RequestMethod.PUT,RequestMethod.GET})
	public String unlinkNid(Integer id) {
		aioSCardService.unlinkNid(id);
		return "redirect:/daioscards";
	}
	
	@RequestMapping(value = "/aiosCard/unlinkDL", method = {RequestMethod.PUT,RequestMethod.GET})
	public String unlinkDL(Integer id) {
		aioSCardService.unlinkDL(id);
		return "redirect:/daioscards";
	}
	
	@RequestMapping(value = "/aiosCard/unlinkHL", method = {RequestMethod.PUT,RequestMethod.GET})
	public String unlinkHL(Integer id) {
		aioSCardService.unlinkHL(id);
		return "redirect:/daioscards";
	}
	
	
}
