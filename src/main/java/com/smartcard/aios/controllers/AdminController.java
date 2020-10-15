package com.smartcard.aios.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.zxing.WriterException;
import com.smartcard.aios.models.Admin;
import com.smartcard.aios.models.AioSCard;
import com.smartcard.aios.models.Citizen;
import com.smartcard.aios.models.District;
import com.smartcard.aios.models.DrivingLicense;
import com.smartcard.aios.models.HealthInsurance;
import com.smartcard.aios.models.NationalId;
import com.smartcard.aios.models.PieStat;
import com.smartcard.aios.models.Village;
import com.smartcard.aios.services.AdminService;
import com.smartcard.aios.services.AioSCardService;
import com.smartcard.aios.services.CitizenService;
import com.smartcard.aios.services.DistrictService;
import com.smartcard.aios.services.DrivingLicenseService;
import com.smartcard.aios.services.HealthInsuranceService;
import com.smartcard.aios.services.NationalIdService;
import com.smartcard.aios.services.PieStatService;
import com.smartcard.aios.services.SectorService;
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
	
	@Autowired
	private AioSCardService aioSCardService;
	
	@Autowired
	private SectorService sectorService;
	
	
	@Autowired
	private NationalIdService nationalIdService;
	
	@Autowired
	private DrivingLicenseService drivingLicenseService;
	
	@Autowired
	private HealthInsuranceService healthInsuranceService;
	

	
	@Autowired
	PieStatService pieStatService;
	
	
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
	
	
	@GetMapping("/ddrivingLicenses")
	public String getDdrivingLicenses(Model model) {
		List<DrivingLicense> activeDlList = drivingLicenseService.activeDlList();
		model.addAttribute("drivingLicenses", activeDlList);
		return "ddrivingLicense";
	}
	
	@GetMapping("/adrivingLicenses")
	public String getinactiveDdrivingLicenses(Model model) {
		List<DrivingLicense> inactiveDlList = drivingLicenseService.inactiveDlList();
		model.addAttribute("drivingLicenses", inactiveDlList);
		return "adrivingLicense";
	}
	
	
	@GetMapping("/udrivingLicenses")
	public String getUdrivingLicenses(Model model) {
		List<DrivingLicense> linkedDlList = drivingLicenseService.linkedDlList();
		model.addAttribute("drivingLicenses", linkedDlList);
		return "udrivingLicense";
	}
	
	@GetMapping("/dhealthInsurances")
	public String getActiveHiList(Model model) {
		List<HealthInsurance> activeHiList = healthInsuranceService.getActiveHiList();
		model.addAttribute("healthInsurances", activeHiList);
		return "dhealthInsurance";
	}
	
	@GetMapping("/ahealthInsurances")
	public String getinActiveHiList(Model model) {
		List<HealthInsurance> inactiveHiList = healthInsuranceService.getinActiveHiList();
		model.addAttribute("healthInsurances", inactiveHiList);
		return "ahealthInsurance";
	}
	
	@GetMapping("/uhealthInsurances")
	public String getLinkedHiList(Model model) {
		List<HealthInsurance> linkHiList = healthInsuranceService.getLinkedHiList();
		model.addAttribute("healthInsurances", linkHiList);
		return "uhealthInsurance";
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
	
	
								// ACTIVE HI
								
								@RequestMapping(value = "/activeAioSCardWithHi", method= {RequestMethod.PUT,RequestMethod.GET})
								public String activeHi(AioSCard aioSCard,String keyword) throws WriterException, IOException {		
									aioSCard = aioSCardService.getAioSCardByKeyword(keyword);
									HealthInsurance healthInsurance = healthInsuranceService.getHealthInsurance(aioSCard.getCitizenUsername());
									healthInsurance.setHi_status("active");
	                                aioSCard.setHealthInsurance(healthInsurance);
	                                aioSCardService.activeHiService(aioSCard, keyword);
									return "redirect:/ahealthInsurances";
								}
								
								// END ACTIVE HI
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//--------------------------------------------------------------------------------------------------------------------
	
	
	// UNUSED
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
