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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.smartcard.aios.models.District;
import com.smartcard.aios.models.DrivingLicense;
import com.smartcard.aios.models.Sector;
import com.smartcard.aios.models.Village;
import com.smartcard.aios.services.CitizenService;
import com.smartcard.aios.services.DistrictService;
import com.smartcard.aios.services.DrivingLicenseService;
import com.smartcard.aios.services.SectorService;
import com.smartcard.aios.services.VillageService;






@Controller
public class DrivingLicenseController {

	@Autowired
	private DrivingLicenseService drivingLicenseService;
	
	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private CitizenService citizenService;
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private VillageService villageService;
	
	@GetMapping("/drivingLicenses")
	public String getDrivingLicenses(Model model,String keyword) {
		
		if(keyword!=null) {
			model.addAttribute("drivingLicenses", drivingLicenseService.findByKeyword(keyword));
		}else {
			//List<DrivingLicense> drivingLicenseList = drivingLicenseService.getDrivingLicenses();
			//model.addAttribute("drivingLicenses", drivingLicenseList);
		}
		List<District> districtList = districtService.getDistricts();
		model.addAttribute("districts", districtList);
		
		return "drivingLicense";
	}
	
	
	@GetMapping("/drivingLicenseCitizenInfos")
	public String getCitizenInfos(Model model,String keyword) {
		if(keyword!=null) {
			model.addAttribute("citizens", citizenService.findByKeyword(keyword));
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
		
		return "drivingLicense";
	}
	
	
	
	
	
	
	@PostMapping("/drivingLicenses/addNew")
	public RedirectView addNew(DrivingLicense drivingLicense,RedirectAttributes redir) {
		
		try {
			
			drivingLicenseService.save(drivingLicense);
			RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","DRIVING LICENSE CARD Generated Successfuly!");
			 return redirectView;
			
		} catch (Exception e) {
			RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","DRIVING LICENSE CARD Generated Successfuly!");
			 return redirectView;
		}
		
		
	}
	
	@RequestMapping("/drivingLicenses/findById")
	@ResponseBody
	public Optional<DrivingLicense> findById(Integer id) {
		return drivingLicenseService.findById(id);
	}
	
	@RequestMapping(value = "/drivingLicenses/update", method= {RequestMethod.PUT,RequestMethod.GET})
	public String update(DrivingLicense drivingLicense) {
	   
		try {
			DrivingLicense dl = drivingLicenseService.getDrivingLicense(drivingLicense.getCitizenUsername());

			dl.setDlplaceIssueId(drivingLicense.getDlplaceIssueId());
			
			drivingLicenseService.update(dl);
		} catch (Exception e) {
			return "error update DL "+e;
		}
		
		
	    return "redirect:/drivingLicenses";
	}
	
	@RequestMapping(value = "/drivingLicenses/addCategoryA", method= {RequestMethod.PUT,RequestMethod.GET})
	public RedirectView addCategoryA(DrivingLicense drivingLicense,String keyword,RedirectAttributes redir) {
	   
		try {
			DrivingLicense dl = drivingLicenseService.getDrivingLicense(keyword);
			dl.setCat_A(drivingLicense.getCat_A());
			dl.setIssuedDate_A(drivingLicense.getIssuedDate_A());
			dl.setExpireDate_A(drivingLicense.getIssuedDate_A().plusYears(10));
			drivingLicenseService.update(dl);
			
			 RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY A ADDED Successfuly!");
			 return redirectView;
			
			
		} catch (Exception e) {
			 RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY A FAILED TO BE ADDED");
			 return redirectView;
		}

	}
	
	
	@RequestMapping(value = "/drivingLicenses/addCategoryB", method= {RequestMethod.PUT,RequestMethod.GET})
	public RedirectView addCategoryB(DrivingLicense drivingLicense,String keyword,RedirectAttributes redir) {
	   
		try {
			DrivingLicense dl = drivingLicenseService.getDrivingLicense(keyword);
			dl.setCat_B(drivingLicense.getCat_B());
			dl.setIssuedDate_B(drivingLicense.getIssuedDate_B());
			dl.setExpireDate_B(drivingLicense.getIssuedDate_B().plusYears(10));
			drivingLicenseService.update(dl);
			
			 RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY B ADDED Successfuly!");
			 return redirectView;
			
		} catch (Exception e) {
			
			 RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY B FAILED TO BE ADDED");
			 return redirectView;
		}
		
		
	}
	
	
	@RequestMapping(value = "/drivingLicenses/addCategoryC", method= {RequestMethod.PUT,RequestMethod.GET})
	public RedirectView addCategoryC(DrivingLicense drivingLicense,String keyword,RedirectAttributes redir) {
	   
		try {
			DrivingLicense dl = drivingLicenseService.getDrivingLicense(keyword);
			dl.setCat_C(drivingLicense.getCat_C());
			dl.setIssuedDate_C(drivingLicense.getIssuedDate_C());
			dl.setExpireDate_C(drivingLicense.getIssuedDate_C().plusYears(10));
			drivingLicenseService.update(dl);
			
			RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY C ADDED Successfuly!");
			 return redirectView;
			
			
		} catch (Exception e) {
			 RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY C FAILED TO BE ADDED");
			 return redirectView;
		}
		
	}
	
	
	@RequestMapping(value = "/drivingLicenses/addCategoryD", method= {RequestMethod.PUT,RequestMethod.GET})
	public RedirectView addCategoryD(DrivingLicense drivingLicense,String keyword,RedirectAttributes redir) {
	   
		try {
			DrivingLicense dl = drivingLicenseService.getDrivingLicense(keyword);
			dl.setCat_D(drivingLicense.getCat_D());
			dl.setIssuedDate_D(drivingLicense.getIssuedDate_D());
			dl.setExpireDate_D(drivingLicense.getIssuedDate_D().plusYears(10));
			drivingLicenseService.update(dl);
			
			RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY D ADDED Successfuly!");
			 return redirectView;
			
			
		} catch (Exception e) {
			 RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY D FAILED TO BE ADDED");
			 return redirectView;
		}

	}
	
	
	
	@RequestMapping(value = "/drivingLicenses/addCategoryE", method= {RequestMethod.PUT,RequestMethod.GET})
	public RedirectView addCategoryE(DrivingLicense drivingLicense,String keyword,RedirectAttributes redir) {
	   
		try {
			DrivingLicense dl = drivingLicenseService.getDrivingLicense(keyword);
			dl.setCat_E(drivingLicense.getCat_E());
			dl.setIssuedDate_E(drivingLicense.getIssuedDate_E());
			dl.setExpireDate_E(drivingLicense.getIssuedDate_E().plusYears(10));
			drivingLicenseService.update(dl);
			
			RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY E ADDED Successfuly!");
			 return redirectView;
			
			
		} catch (Exception e) {
			RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY E FAILED TO BE ADDED");
			 return redirectView;
		}

	}
	
	
	@RequestMapping(value = "/drivingLicenses/addCategoryA1", method= {RequestMethod.PUT,RequestMethod.GET})
	public RedirectView addCategoryA1(DrivingLicense drivingLicense,String keyword,RedirectAttributes redir) {
	   
		try {
			DrivingLicense dl = drivingLicenseService.getDrivingLicense(keyword);
			dl.setCat_A1(drivingLicense.getCat_A1());
			dl.setIssuedDate_A1(drivingLicense.getIssuedDate_A1());
			dl.setExpireDate_A1(drivingLicense.getIssuedDate_A1().plusYears(10));
			drivingLicenseService.update(dl);
			
			RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY A1 ADDED Successfuly!");
			 return redirectView;
			
			
		} catch (Exception e) {
			RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY A1 FAILED TO BE ADDED");
			 return redirectView;
		}
		
		
	}
	
	
	@RequestMapping(value = "/drivingLicenses/addCategoryB1", method= {RequestMethod.PUT,RequestMethod.GET})
	public RedirectView addCategoryB1(DrivingLicense drivingLicense,String keyword,RedirectAttributes redir) {
	   
		try {
			DrivingLicense dl = drivingLicenseService.getDrivingLicense(keyword);
			dl.setCat_B1(drivingLicense.getCat_B1());
			dl.setIssuedDate_B1(drivingLicense.getIssuedDate_B1());
			dl.setExpireDate_B1(drivingLicense.getIssuedDate_B1().plusYears(10));
			drivingLicenseService.update(dl);
			RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY B1 ADDED Successfuly!");
			 return redirectView;
			
		} catch (Exception e) {
			RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY B1 FAILED TO BE ADDED");
			 return redirectView;
		}
	
	}
	
	
	@RequestMapping(value = "/drivingLicenses/addCategoryD1", method= {RequestMethod.PUT,RequestMethod.GET})
	public RedirectView addCategoryD1(DrivingLicense drivingLicense,String keyword,RedirectAttributes redir) {
	   
		try {
			DrivingLicense dl = drivingLicenseService.getDrivingLicense(keyword);
			dl.setCat_D1(drivingLicense.getCat_D1());
			dl.setIssuedDate_D1(drivingLicense.getIssuedDate_D1());
			dl.setExpireDate_D1(drivingLicense.getIssuedDate_D1().plusYears(10));
			drivingLicenseService.update(dl);
			
			RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY D1 ADDED Successfuly!");
			 return redirectView;
			
			
		} catch (Exception e) {
			RedirectView  redirectView = new RedirectView("/drivingLicenses",true);
			 redir.addFlashAttribute("reqM","CATEGORY D1 FAILED TO BE ADDED");
			 return redirectView;
		}

	}
	
	
	@RequestMapping(value = "/drivingLicenses/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	public String delete(Integer id) {
	    drivingLicenseService.delete(id);
	    return "redirect:/drivingLicenses";
	}
	
	
	
}
