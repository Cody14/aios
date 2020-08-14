package com.smartcard.aios.services;



import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.Year;
import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.header.writers.frameoptions.StaticAllowFromStrategy;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.smartcard.aios.models.AioSCard;
import com.smartcard.aios.models.Citizen;
import com.smartcard.aios.repositories.AioSCardRepository;
import com.smartcard.aios.repositories.CitizenRepository;






@Service
@Transactional
public class AioSCardService {
	
	@Autowired
	private AioSCardRepository aioSCardRepository;
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	int year = Year.now().getValue();
	
	
	private static String QRSCANCODEPATH = "C:\\Users\\Theophile\\Desktop\\Vali aios\\aios\\aios\\src\\main\\resources\\static\\uploads\\";
	
	//return List of all aioSCards
		public List<AioSCard> getAioSCards(){
			return aioSCardRepository.findAll();
		}
		
		public AioSCard getAioSCard(String citizenUsername) {
			return aioSCardRepository.findByCitizenUsername(citizenUsername);
		}
		

		
		public void save(AioSCard aioSCard) throws WriterException, IOException {
			
			int aioscardnop1 = (int)((Math.random() * 9000)+1000);
			int aioscardnop2 = (int)((Math.random() * 9000)+1000);
			int aioscardnop3 = (int)((Math.random() * 9000)+1000);
			int aioscardnop4 = (int)((Math.random() * 9000)+1000);
			
			
			
			int cvv = (int)((Math.random() * 900)+100);
			
			int littNo = (int)((Math.random() * 900)+100);
			
		    Citizen citizen = citizenRepository.findAllByUsername(aioSCard.getCitizenUsername());
		    
		    QRCodeWriter writer = new QRCodeWriter();
		    
		    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
		    int yearDiff = year - citizen.getDateOfBirth().getYear();
		     
		    BitMatrix bitMatrix = writer.encode(		
		    citizen.getFirstname()+" "+citizen.getLastname()+"\n"
		    +yearDiff+"\n"+
		    	   citizen.getGender()+"\n"+
		            citizen.getMartialStatus()
		            ,BarcodeFormat.QR_CODE, 350, 350);
		    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
			MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
			
		   
			
		    if(citizen.getGender().equalsIgnoreCase("M")) {
		    	
		    	    aioSCard.setAioscardNo(""+aioscardnop1+" "+" "+aioscardnop2+" "+aioscardnop3+" "+aioscardnop4);
				    aioSCard.setCvv(""+cvv);
				    aioSCard.setLittleCardNo(""+littNo);
					aioSCard.setCitizen(citizen);
					aioSCard.setOwnerStatus("MR.");
					aioSCard.setQrcodePhoto(qrcodePicture);
					aioSCard.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
					aioSCard.setOwnerGender(citizen.getGender());
					aioSCard.setOwnerDob(citizen.getDateOfBirth());
					aioSCard.setPhoto(citizen.getPhoto());
					aioSCard.setExpireDate(aioSCard.getIssuedDate().plusYears(10));
				    aioSCardRepository.save(aioSCard);
		    	
		    }if(citizen.getGender().equalsIgnoreCase("F")) {
		    	
		    	aioSCard.setAioscardNo(""+aioscardnop1+" "+" "+aioscardnop2+" "+aioscardnop3+" "+aioscardnop4);
			    aioSCard.setCvv(""+cvv);
			    aioSCard.setLittleCardNo(""+littNo);
			    aioSCard.setOwnerStatus("MRS.");
				aioSCard.setCitizen(citizen);
				aioSCard.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
				aioSCard.setOwnerGender(citizen.getGender());
				aioSCard.setOwnerDob(citizen.getDateOfBirth());
				aioSCard.setPhoto(citizen.getPhoto());
				aioSCard.setExpireDate(aioSCard.getIssuedDate().plusYears(10));
				String dat = ""+aioSCard.getIssuedDate().getMonthValue()+"/"+aioSCard.getIssuedDate().getYear();
			    aioSCardRepository.save(aioSCard);
		    	
		    }
		
		   
		
		}
		
		// find a aioSCard by id
		
		public Optional<AioSCard> findById (Integer id) {
			return aioSCardRepository.findById(id);

		}
		
		public void update(AioSCard aioSCard) {
			aioSCardRepository.save(aioSCard);
		}
		
		public void delete(Integer id) {
			aioSCardRepository.deleteById(id);
		}
		
		// Get aioSCard by keyword
		
		public List<AioSCard> findByKeyword(String keyword){
			return aioSCardRepository.findByKeyowrd(keyword);
		}		
		
		
}
