package com.smartcard.aios.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

import com.google.zxing.WriterException;
import com.smartcard.aios.models.AdminMessage;
import com.smartcard.aios.models.AioSCard;
import com.smartcard.aios.models.DrivingLicense;
import com.smartcard.aios.models.Sector;
import com.smartcard.aios.services.AdminMessageService;
import com.smartcard.aios.services.SectorService;



@Controller
public class AdminMessageController {

	@Autowired
	private AdminMessageService adminMessageService;
	
	
	
	
	
	
	@GetMapping("/adminMessages")
	public String getAdminMessages(Model model) {
		
		List<AdminMessage> adminMessageList = adminMessageService.getAdminMessages();

		Collections.reverse(adminMessageList); 
		
		model.addAttribute("adminMessages", adminMessageList);
	
		return "adminMessage";
	}
	
	
	@RequestMapping(value = "/seenAdminMessage", method= {RequestMethod.PUT,RequestMethod.GET})
	public String seenAdminMessage(AdminMessage adminMessage,String username) throws WriterException, IOException {
		
		adminMessage = adminMessageService.getAdminMessage(username);

	    adminMessage.setMessageStatus("seen");
		
	    adminMessageService.save(adminMessage);
		
		return "redirect:/adminMessages";
	}
	
	
	
	@PostMapping("/adminMessages/addNew")
	public RedirectView addNew(AdminMessage adminMessage,RedirectAttributes redir) {
		
		try {
			 adminMessageService.save(adminMessage);
			 RedirectView  redirectView = new RedirectView("/contactUs",true);
			 redir.addFlashAttribute("reqM","Message Sent Successfuly!");
			 return redirectView;
			
		} catch (Exception e) {
			 RedirectView  redirectView = new RedirectView("/contactUs",true);
			 redir.addFlashAttribute("reqM","Message Failed");
			 return redirectView;
		}

	}
	
	@RequestMapping("/adminMessages/findById")
	@ResponseBody
	public Optional<AdminMessage> findById(Integer id) {
		return adminMessageService.findById(id);
	}
	
	@RequestMapping(value = "/adminMessages/update", method= {RequestMethod.PUT,RequestMethod.GET})
	public String update(AdminMessage adminMessage) {
	    adminMessageService.update(adminMessage);
	    return "redirect:/adminMessages";
	}
	
	
	@RequestMapping(value = "/adminMessages/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	public String delete(Integer id) {
	    adminMessageService.delete(id);
	    return "redirect:/adminMessages";
	}
	
	
}
