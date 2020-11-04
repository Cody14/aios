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
import com.smartcard.aios.models.DrivingLicense;
import com.smartcard.aios.models.HealthInsurance;
import com.smartcard.aios.models.NationalId;
import com.smartcard.aios.repositories.AioSCardRepository;
import com.smartcard.aios.repositories.CitizenRepository;
import com.smartcard.aios.repositories.DrivingLicenseRepository;
import com.smartcard.aios.repositories.HealthInsuranceRepository;
import com.smartcard.aios.repositories.NationalIdRepository;






@Service
@Transactional
public class AioSCardService {
	
	@Autowired
	private AioSCardRepository aioSCardRepository;
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	@Autowired
	private NationalIdRepository nationalIdRepository;
	
	@Autowired
	private DrivingLicenseRepository drivingLicenseRepository;
	
	@Autowired
	private HealthInsuranceRepository healthInsuranceRepository;
	
	int year = Year.now().getValue();
	
	
	private static String QRSCANCODEPATH = "C:\\Users\\Theophile\\Desktop\\aiosmartcard\\aios\\aios\\src\\main\\resources\\static\\uploads\\";
	
	//return List of all aioSCards
		public List<AioSCard> getAioSCards(){
			return aioSCardRepository.findAll();
		}
		
		public AioSCard getAioSCard(String citizenUsername) {
			return aioSCardRepository.findByCitizenUsername(citizenUsername);
		}
		
		public AioSCard getAioSCardByKeyword(String keyword) {
			return aioSCardRepository.findByKeywordUsername(keyword);
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

		
		    	
		    	  BitMatrix bitMatrix = writer.encode(		
		    			  "NAME   : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
		    	          "OTHER CARDS : NOT LINKED"
		    			   ,BarcodeFormat.QR_CODE, 350, 350);
		    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
		    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	    
		  
		   
			
		    if(citizen.getGender().equalsIgnoreCase("Male")) {
		    	
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
		    	
		    }if(citizen.getGender().equalsIgnoreCase("Female")) {
		    	
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
		
		
		public void linkNidService(AioSCard aioSCard,String keyword) throws WriterException, IOException {
			
			aioSCard = aioSCardRepository.findByKeywordUsername(keyword);

		    Citizen citizen = citizenRepository.findAllByUsername(aioSCard.getCitizenUsername());
		    
		   
		    
		    NationalId nationalId = nationalIdRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		    
		    DrivingLicense drivingLicense = drivingLicenseRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		    
		    HealthInsurance healthInsurance = healthInsuranceRepository.findByCitizenUsername(aioSCard.getCitizenUsername());

		    if(aioSCard.getDrivingLicense()==null && aioSCard.getHealthInsurance()==null& nationalId.getNidStatus()=="active") {
		    	 QRCodeWriter writer = new QRCodeWriter();
				    
				    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
				
				    	
				    	  BitMatrix bitMatrix = writer.encode(
				    			  
				    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
				    			  "NID No   : "+  nationalId.getNidNo()
				    			            ,BarcodeFormat.QR_CODE, 350, 350);
				    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
				    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
				nationalId.setLinkStatus("linked");
				nationalId.setNidStatus("active");
				aioSCard.setNationalId(nationalId);
				aioSCard.setQrcodePhoto(qrcodePicture);
				aioSCardRepository.save(aioSCard);
		
		    }else if(aioSCard.getDrivingLicense()!=null && aioSCard.getHealthInsurance()==null& nationalId.getNidStatus()=="active") {
		    	
		    	QRCodeWriter writer = new QRCodeWriter();
			    
			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
			
			    	
			    	  BitMatrix bitMatrix = writer.encode(
			    			  
			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
			    			  "NID No   : "+  nationalId.getNidNo()+"\n"+
			    		      "DL No    : "+ drivingLicense.getDlNo()			  
			    			            ,BarcodeFormat.QR_CODE, 350, 350);
			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
			 nationalId.setLinkStatus("linked");
			 nationalId.setNidStatus("active");
			aioSCard.setNationalId(nationalId);
			aioSCard.setQrcodePhoto(qrcodePicture);
			aioSCardRepository.save(aioSCard);
	
		    	
		    }else if(aioSCard.getDrivingLicense()==null && aioSCard.getHealthInsurance()!=null& nationalId.getNidStatus()=="active") {
		    	
                QRCodeWriter writer = new QRCodeWriter();
			    
			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
			
			    	
			    	  BitMatrix bitMatrix = writer.encode(
			    			  
			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
			    			  "NID No   : "+  nationalId.getNidNo()+"\n"+
			    		      "HL No    : "+ healthInsurance.getRssbNo()			  
			    			            ,BarcodeFormat.QR_CODE, 350, 350);
			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
			nationalId.setLinkStatus("linked");
			nationalId.setNidStatus("active");
			aioSCard.setNationalId(nationalId);
			aioSCard.setQrcodePhoto(qrcodePicture);
			aioSCardRepository.save(aioSCard);
		    	
		    }else if(aioSCard.getDrivingLicense()!=null && aioSCard.getHealthInsurance()!=null& nationalId.getNidStatus()=="active") {
		    	
		    	 QRCodeWriter writer = new QRCodeWriter();
				    
				    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
				
				    	
				    	  BitMatrix bitMatrix = writer.encode(
				    			  
				    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
				    			  "NID No   : "+  nationalId.getNidNo()+"\n"+
				    			  "DL No    : "+ drivingLicense.getDlNo()+"\n"+		  
				    		      "HL No    : "+ healthInsurance.getRssbNo()			  
				    			            ,BarcodeFormat.QR_CODE, 350, 350);
				    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
				    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
				nationalId.setLinkStatus("linked");
				nationalId.setNidStatus("active");
				aioSCard.setNationalId(nationalId);
				aioSCard.setQrcodePhoto(qrcodePicture);
				aioSCardRepository.save(aioSCard);
		    	
		    }
		    
		   
			
		}
		
		// UNLINK NID
		
           public void UNLINKNid1Service(AioSCard aioSCard,String keyword) throws WriterException, IOException {
			
			aioSCard = aioSCardRepository.findByKeywordUsername(keyword);

		    Citizen citizen = citizenRepository.findAllByUsername(aioSCard.getCitizenUsername());
		    
		    NationalId nationalId = nationalIdRepository.findByCitizenUsername(aioSCard.getCitizenUsername());

		    
		    DrivingLicense drivingLicense = drivingLicenseRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		    
		    HealthInsurance healthInsurance = healthInsuranceRepository.findByCitizenUsername(aioSCard.getCitizenUsername());

		    if(aioSCard.getDrivingLicense()==null && aioSCard.getHealthInsurance()==null) {
		    	 QRCodeWriter writer = new QRCodeWriter();
				    
				    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
				
				    	
				    	  BitMatrix bitMatrix = writer.encode(
				    			  
				    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
				    			  "NID No   : NID IS UNLINKED"
				    			            ,BarcodeFormat.QR_CODE, 350, 350);
				    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
				    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
				nationalId.setLinkStatus("unlinked");
				aioSCard.setNationalId(null);
				aioSCard.setQrcodePhoto(qrcodePicture);
				aioSCardRepository.save(aioSCard);
		
		    }else if(aioSCard.getDrivingLicense()!=null && aioSCard.getHealthInsurance()==null) {
		    	
		    	QRCodeWriter writer = new QRCodeWriter();
			    
			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
			
			    	
			    	  BitMatrix bitMatrix = writer.encode(
			    			  
			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
			    			  "NID No   : UNLINKED"+"\n"+
			    		      "DL No    : "+ drivingLicense.getDlNo()			  
			    			            ,BarcodeFormat.QR_CODE, 350, 350);
			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
			nationalId.setLinkStatus("unlinked");
			aioSCard.setNationalId(null);
			aioSCard.setQrcodePhoto(qrcodePicture);
			aioSCardRepository.save(aioSCard);
	
		    	
		    }else if(aioSCard.getDrivingLicense()==null && aioSCard.getHealthInsurance()!=null) {
		    	
                QRCodeWriter writer = new QRCodeWriter();
			    
			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
			
			    	
			    	  BitMatrix bitMatrix = writer.encode(
			    			  
			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
			    			  "NID No   : UNLINKED"+"\n"+
			    		      "HL No    : "+ healthInsurance.getRssbNo()			  
			    			            ,BarcodeFormat.QR_CODE, 350, 350);
			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
			nationalId.setLinkStatus("unlinked");
			aioSCard.setNationalId(null);
			aioSCard.setQrcodePhoto(qrcodePicture);
			aioSCardRepository.save(aioSCard);
		    	
		    }else if(aioSCard.getDrivingLicense()!=null && aioSCard.getHealthInsurance()!=null) {
		    	
		    	 QRCodeWriter writer = new QRCodeWriter();
				    
				    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
				
				    	
				    	  BitMatrix bitMatrix = writer.encode(
				    			  
				    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
				    			  "NID No   : UNLINKED"+"\n"+
				    			  "DL No    : "+ drivingLicense.getDlNo()+"\n"+		  
				    		      "HL No    : "+ healthInsurance.getRssbNo()			  
				    			            ,BarcodeFormat.QR_CODE, 350, 350);
				    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
				    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
				nationalId.setLinkStatus("unlinked");
				aioSCard.setNationalId(null);
				aioSCard.setQrcodePhoto(qrcodePicture);
				aioSCardRepository.save(aioSCard);
		    	
		    }
		    
		   
			
		}
		
		
		
		// END OF UNLINK NID
           
           
           // DISABLE NID
           
           
           public void disableNid1Service(AioSCard aioSCard,String keyword) throws WriterException, IOException {
   			
   			aioSCard = aioSCardRepository.findByKeywordUsername(keyword);

   		    Citizen citizen = citizenRepository.findAllByUsername(aioSCard.getCitizenUsername());
   		    
		    NationalId nationalId = nationalIdRepository.findByCitizenUsername(aioSCard.getCitizenUsername());

   		    
   		    DrivingLicense drivingLicense = drivingLicenseRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
   		    
   		    HealthInsurance healthInsurance = healthInsuranceRepository.findByCitizenUsername(aioSCard.getCitizenUsername());

   		    if(aioSCard.getDrivingLicense()==null && aioSCard.getHealthInsurance()==null) {
   		    	 QRCodeWriter writer = new QRCodeWriter();
   				    
   				    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
   				
   				    	
   				    	  BitMatrix bitMatrix = writer.encode(
   				    			  
   				    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
   				    			  "NID No   : DISABLED"
   				    			            ,BarcodeFormat.QR_CODE, 350, 350);
   				    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
   				    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
   				
   				nationalId.setNidStatus("inactive");    				
   				aioSCard.setNationalId(nationalId);
   				aioSCard.setQrcodePhoto(qrcodePicture);
   				aioSCardRepository.save(aioSCard);
   		
   		    }else if(aioSCard.getDrivingLicense()!=null && aioSCard.getHealthInsurance()==null) {
   		    	
   		    	QRCodeWriter writer = new QRCodeWriter();
   			    
   			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
   			
   			    	
   			    	  BitMatrix bitMatrix = writer.encode(
   			    			  
   			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
   			    			  "NID No   : DISABLED"+"\n"+
   			    		      "DL No    : "+ drivingLicense.getDlNo()			  
   			    			            ,BarcodeFormat.QR_CODE, 350, 350);
   			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
   			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
   			
   			nationalId.setNidStatus("inactive");    				
   			aioSCard.setNationalId(nationalId);
   			aioSCard.setQrcodePhoto(qrcodePicture);
   			aioSCardRepository.save(aioSCard);
   	
   		    	
   		    }else if(aioSCard.getDrivingLicense()==null && aioSCard.getHealthInsurance()!=null) {
   		    	
                   QRCodeWriter writer = new QRCodeWriter();
   			    
   			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
   			
   			    	
   			    	  BitMatrix bitMatrix = writer.encode(
   			    			  
   			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
   			    			  "NID No   : DISABLED"+"\n"+
   			    		      "HL No    : "+ healthInsurance.getRssbNo()			  
   			    			            ,BarcodeFormat.QR_CODE, 350, 350);
   			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
   			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
   			nationalId.setNidStatus("inactive");
   			aioSCard.setNationalId(nationalId);
   			aioSCard.setQrcodePhoto(qrcodePicture);
   			aioSCardRepository.save(aioSCard);
   		    	
   		    }else if(aioSCard.getDrivingLicense()!=null && aioSCard.getHealthInsurance()!=null) {
   		    	
   		    	 QRCodeWriter writer = new QRCodeWriter();
   				    
   				    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
   				
   				    	
   				    	  BitMatrix bitMatrix = writer.encode(
   				    			  
   				    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
   				    			  "NID No   : DISABLED"+"\n"+
   				    			  "DL No    : "+ drivingLicense.getDlNo()+"\n"+		  
   				    		      "HL No    : "+ healthInsurance.getRssbNo()			  
   				    			            ,BarcodeFormat.QR_CODE, 350, 350);
   				    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
   				    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
   				nationalId.setNidStatus("inactive");
   				aioSCard.setNationalId(nationalId);
   				aioSCard.setQrcodePhoto(qrcodePicture);
   				aioSCardRepository.save(aioSCard);
   		    	
   		    }
   		    
   		   
   			
   		}
           
           
           
           
           
           
           // END DISABEL NID
           
           
            // ACTIVATE NID
           
           
           public void activeNid1Service(AioSCard aioSCard,String keyword) throws WriterException, IOException {
   			
   			aioSCard = aioSCardRepository.findByKeywordUsername(keyword);

   		    Citizen citizen = citizenRepository.findAllByUsername(aioSCard.getCitizenUsername());
   		    
		    NationalId nationalId = nationalIdRepository.findByCitizenUsername(aioSCard.getCitizenUsername());

   		    
   		    DrivingLicense drivingLicense = drivingLicenseRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
   		    
   		    HealthInsurance healthInsurance = healthInsuranceRepository.findByCitizenUsername(aioSCard.getCitizenUsername());

   		    if(aioSCard.getDrivingLicense()==null && aioSCard.getHealthInsurance()==null) {
   		    	 QRCodeWriter writer = new QRCodeWriter();
   				    
   				    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
   				
   				    	
   				    	  BitMatrix bitMatrix = writer.encode(
   				    			  
   				    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
   				    			  "NID act : "+nationalId.getNidNo()
   				    			            ,BarcodeFormat.QR_CODE, 350, 350);
   				    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
   				    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
   				
   				nationalId.setNidStatus("active");    				
   				aioSCard.setNationalId(nationalId);
   				aioSCard.setQrcodePhoto(qrcodePicture);
   				aioSCardRepository.save(aioSCard);
   		
   		    }else if(aioSCard.getDrivingLicense()!=null && aioSCard.getHealthInsurance()==null) {
   		    	
   		    	QRCodeWriter writer = new QRCodeWriter();
   			    
   			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
   			
   			    	
   			    	  BitMatrix bitMatrix = writer.encode(
   			    			  
   			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
   			    			  "NID act : "+nationalId.getNidNo()+"\n"+
   			    		      "DL No    : "+ drivingLicense.getDlNo()			  
   			    			            ,BarcodeFormat.QR_CODE, 350, 350);
   			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
   			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
   			
   			nationalId.setNidStatus("active");    				
   			aioSCard.setNationalId(nationalId);
   			aioSCard.setQrcodePhoto(qrcodePicture);
   			aioSCardRepository.save(aioSCard);
   	
   		    	
   		    }else if(aioSCard.getDrivingLicense()==null && aioSCard.getHealthInsurance()!=null) {
   		    	
                   QRCodeWriter writer = new QRCodeWriter();
   			    
   			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
   			
   			    	
   			    	  BitMatrix bitMatrix = writer.encode(
   			    			  
   			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
   			    			  "NID act :"+nationalId.getNidNo()+"\n"+
   			    		      "HL No    : "+ healthInsurance.getRssbNo()			  
   			    			            ,BarcodeFormat.QR_CODE, 350, 350);
   			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
   			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
   			nationalId.setNidStatus("active");
   			aioSCard.setNationalId(nationalId);
   			aioSCard.setQrcodePhoto(qrcodePicture);
   			aioSCardRepository.save(aioSCard);
   		    	
   		    }else if(aioSCard.getDrivingLicense()!=null && aioSCard.getHealthInsurance()!=null) {
   		    	
   		    	 QRCodeWriter writer = new QRCodeWriter();
   				    
   				    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
   				
   				    	
   				    	  BitMatrix bitMatrix = writer.encode(
   				    			  
   				    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
   				    			  "NID act : "+nationalId.getNidNo()+"\n"+
   				    			  "DL No    : "+ drivingLicense.getDlNo()+"\n"+		  
   				    		      "HL No    : "+ healthInsurance.getRssbNo()			  
   				    			            ,BarcodeFormat.QR_CODE, 350, 350);
   				    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
   				    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
   				nationalId.setNidStatus("active");
   				aioSCard.setNationalId(nationalId);
   				aioSCard.setQrcodePhoto(qrcodePicture);
   				aioSCardRepository.save(aioSCard);
   		    	
   		    }
   		    
   		   
   			
   		}
           
           
           
           
           
           
           // END DISABE NID
		
		
		
		
		
		
		
        public void linkDlService(AioSCard aioSCard,String keyword) throws WriterException, IOException {
			
			aioSCard = aioSCardRepository.findByKeywordUsername(keyword);

		    Citizen citizen = citizenRepository.findAllByUsername(aioSCard.getCitizenUsername());
		    
		     NationalId nationalId = nationalIdRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		     
		     HealthInsurance healthInsurance = healthInsuranceRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		    
		    DrivingLicense drivingLicense = drivingLicenseRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		    
             if(aioSCard.getNationalId()==null && aioSCard.getHealthInsurance()==null) {
            	 QRCodeWriter writer = new QRCodeWriter();
 			    
 			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
 			    
 			
 			    	
 			    	  BitMatrix bitMatrix = writer.encode(
 			    			  
 			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
 			    		      "DL No   : "+ drivingLicense.getDlNo()			  
 			    			            ,BarcodeFormat.QR_CODE, 350, 350);
 			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
 			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            drivingLicense.setDlStatus("active");
            drivingLicense.setLinkStatus("linked");
 			aioSCard.setDrivingLicense(drivingLicense);
 			aioSCard.setQrcodePhoto(qrcodePicture);
 			aioSCardRepository.save(aioSCard);
 		    
             }else if(aioSCard.getNationalId()!=null && aioSCard.getHealthInsurance()==null) {
            	 QRCodeWriter writer = new QRCodeWriter();
  			    
  			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
  			    
  			
  			    	
  			    	  BitMatrix bitMatrix = writer.encode(
  			    			  
  			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
  			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
  			    		      "DL No   : "+ drivingLicense.getDlNo()			  
  			    			            ,BarcodeFormat.QR_CODE, 350, 350);
  			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
  			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
  			drivingLicense.setDlStatus("active");
  			drivingLicense.setLinkStatus("linked");
  			aioSCard.setDrivingLicense(drivingLicense);
  			aioSCard.setQrcodePhoto(qrcodePicture);
  			aioSCardRepository.save(aioSCard);
            	 
             }else if(aioSCard.getNationalId()==null && aioSCard.getHealthInsurance()!=null) {
            	 QRCodeWriter writer = new QRCodeWriter();
   			    
   			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
   			    
   			
   			    	
   			    	  BitMatrix bitMatrix = writer.encode(
   			    			  
   			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
   			    			  "DL No  : "+ drivingLicense.getDlNo()+"\n"+
   			    		      "HL No   : "+ healthInsurance.getRssbNo()			  
   			    			            ,BarcodeFormat.QR_CODE, 350, 350);
   			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
   			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
   			drivingLicense.setDlStatus("active");
   			drivingLicense.setLinkStatus("linked");
   			aioSCard.setDrivingLicense(drivingLicense);
   			aioSCard.setQrcodePhoto(qrcodePicture);
   			aioSCardRepository.save(aioSCard);
             }else if(aioSCard.getNationalId()!=null && aioSCard.getHealthInsurance()!=null) {
            	 QRCodeWriter writer = new QRCodeWriter();
    			    
    			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
    			    
    			
    			    	
    			    	  BitMatrix bitMatrix = writer.encode(
    			    			  
    			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
    			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
    			    			  "DL No   : "+ drivingLicense.getDlNo()+"\n"+			  
    			    		      "HL No   : "+ healthInsurance.getRssbNo()			  
    			    			            ,BarcodeFormat.QR_CODE, 350, 350);
    			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
    			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    			drivingLicense.setDlStatus("active");
    			drivingLicense.setLinkStatus("linked");
    			aioSCard.setDrivingLicense(drivingLicense);
    			aioSCard.setQrcodePhoto(qrcodePicture);
    			aioSCardRepository.save(aioSCard);
             }
            
		   
			
			 
		}
		
        
        // UNLINK DL
        
        
     public void UNLINLDlService(AioSCard aioSCard,String keyword) throws WriterException, IOException {
			
			aioSCard = aioSCardRepository.findByKeywordUsername(keyword);

		    Citizen citizen = citizenRepository.findAllByUsername(aioSCard.getCitizenUsername());
		    
		     NationalId nationalId = nationalIdRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		     
		     HealthInsurance healthInsurance = healthInsuranceRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		    
			    DrivingLicense drivingLicense = drivingLicenseRepository.findByCitizenUsername(aioSCard.getCitizenUsername());

		    
             if(aioSCard.getNationalId()==null && aioSCard.getHealthInsurance()==null) {
            	 QRCodeWriter writer = new QRCodeWriter();
 			    
 			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
 			    
 			
 			    	
 			    	  BitMatrix bitMatrix = writer.encode(
 			    			  
 			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
 			    		      "DL    : UNLINKED"			  
 			    			            ,BarcodeFormat.QR_CODE, 350, 350);
 			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
 			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            drivingLicense.setLinkStatus("unlinked");
 			aioSCard.setDrivingLicense(null);
 			aioSCard.setQrcodePhoto(qrcodePicture);
 			aioSCardRepository.save(aioSCard);
 		    
             }else if(aioSCard.getNationalId()!=null && aioSCard.getHealthInsurance()==null) {
            	 QRCodeWriter writer = new QRCodeWriter();
  			    
  			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
  			    
  			
  			    	
  			    	  BitMatrix bitMatrix = writer.encode(
  			    			  
  			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
  			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
  			    		      "DL    : UNLINKED"			  
  			    			            ,BarcodeFormat.QR_CODE, 350, 350);
  			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
  			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
  			drivingLicense.setLinkStatus("unlinked");
  			aioSCard.setDrivingLicense(null);
  			aioSCard.setQrcodePhoto(qrcodePicture);
  			aioSCardRepository.save(aioSCard);
            	 
             }else if(aioSCard.getNationalId()==null && aioSCard.getHealthInsurance()!=null) {
            	 QRCodeWriter writer = new QRCodeWriter();
   			    
   			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
   			    
   			
   			    	
   			    	  BitMatrix bitMatrix = writer.encode(
   			    			  
   			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
   			    			  "DL    : UNLINKED"+"\n"+
   			    		      "HL No   : "+ healthInsurance.getRssbNo()			  
   			    			            ,BarcodeFormat.QR_CODE, 350, 350);
   			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
   			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
   			drivingLicense.setLinkStatus("unlinked");
   			aioSCard.setDrivingLicense(null);
   			aioSCard.setQrcodePhoto(qrcodePicture);
   			aioSCardRepository.save(aioSCard);
             }else if(aioSCard.getNationalId()!=null && aioSCard.getHealthInsurance()!=null) {
            	 QRCodeWriter writer = new QRCodeWriter();
    			    
    			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
    			    
    			
    			    	
    			    	  BitMatrix bitMatrix = writer.encode(
    			    			  
    			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
    			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
    			    			  "DL    : UNLINKED"+"\n"+			  
    			    		      "HL No   : "+ healthInsurance.getRssbNo()			  
    			    			            ,BarcodeFormat.QR_CODE, 350, 350);
    			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
    			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    			drivingLicense.setLinkStatus("unlinked");
    			aioSCard.setDrivingLicense(null);
    			aioSCard.setQrcodePhoto(qrcodePicture);
    			aioSCardRepository.save(aioSCard);
             }
            
		   
			
			 
		}
        
        
        // END UNLINK DL
     
     
      // DISABLE DL
     
     
     public void disableDlService(AioSCard aioSCard,String keyword) throws WriterException, IOException {
			
			aioSCard = aioSCardRepository.findByKeywordUsername(keyword);

		    Citizen citizen = citizenRepository.findAllByUsername(aioSCard.getCitizenUsername());
		    
		     NationalId nationalId = nationalIdRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		     
		     HealthInsurance healthInsurance = healthInsuranceRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		     
		     DrivingLicense drivingLicense = drivingLicenseRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		    
		    
             if(aioSCard.getNationalId()==null && aioSCard.getHealthInsurance()==null) {
            	 QRCodeWriter writer = new QRCodeWriter();
 			    
 			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
 			    
 			
 			    	
 			    	  BitMatrix bitMatrix = writer.encode(
 			    			  
 			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
 			    		      "DL    : DISABLED"			  
 			    			            ,BarcodeFormat.QR_CODE, 350, 350);
 			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
 			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            drivingLicense.setDlStatus("inactive");
 			aioSCard.setDrivingLicense(drivingLicense);
 			aioSCard.setQrcodePhoto(qrcodePicture);
 			aioSCardRepository.save(aioSCard);
 		    
             }else if(aioSCard.getNationalId()!=null && aioSCard.getHealthInsurance()==null) {
            	 QRCodeWriter writer = new QRCodeWriter();
  			    
  			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
  			    
  			
  			    	
  			    	  BitMatrix bitMatrix = writer.encode(
  			    			  
  			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
  			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
  			    		      "DL    : DISABLED"			  
  			    			            ,BarcodeFormat.QR_CODE, 350, 350);
  			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
  			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
             
  			drivingLicense.setDlStatus("inactive");
  			aioSCard.setDrivingLicense(drivingLicense);
  			aioSCard.setQrcodePhoto(qrcodePicture);
  			aioSCardRepository.save(aioSCard);
            	 
             }else if(aioSCard.getNationalId()==null && aioSCard.getHealthInsurance()!=null) {
            	 QRCodeWriter writer = new QRCodeWriter();
   			    
   			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
   			    
   			
   			    	
   			    	  BitMatrix bitMatrix = writer.encode(
   			    			  
   			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
   			    			  "DL    : DISABLED"+"\n"+
   			    		      "HL No   : "+ healthInsurance.getRssbNo()			  
   			    			            ,BarcodeFormat.QR_CODE, 350, 350);
   			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
   			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            drivingLicense.setDlStatus("inactive");
   			aioSCard.setDrivingLicense(drivingLicense);
   			aioSCard.setQrcodePhoto(qrcodePicture);
   			aioSCardRepository.save(aioSCard);
             }else if(aioSCard.getNationalId()!=null && aioSCard.getHealthInsurance()!=null) {
            	 QRCodeWriter writer = new QRCodeWriter();
    			    
    			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
    			    
    			
    			    	
    			    	  BitMatrix bitMatrix = writer.encode(
    			    			  
    			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
    			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
    			    			  "DL      : DISABLED"+"\n"+			  
    			    		      "HL No   : "+ healthInsurance.getRssbNo()			  
    			    			            ,BarcodeFormat.QR_CODE, 350, 350);
    			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
    			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
               drivingLicense.setDlStatus("inactive");
    			aioSCard.setDrivingLicense(drivingLicense);
    			aioSCard.setQrcodePhoto(qrcodePicture);
    			aioSCardRepository.save(aioSCard);
             }
            
		   
			
			 
		}
        
        
        // END DISABLE DL
     
      // ACTIVE DL
     
     
     public void activeDlService(AioSCard aioSCard,String keyword) throws WriterException, IOException {
			
			aioSCard = aioSCardRepository.findByKeywordUsername(keyword);

		    Citizen citizen = citizenRepository.findAllByUsername(aioSCard.getCitizenUsername());
		    
		     NationalId nationalId = nationalIdRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		     
		     HealthInsurance healthInsurance = healthInsuranceRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		     
		     DrivingLicense drivingLicense = drivingLicenseRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		    
		    
             if(aioSCard.getNationalId()==null && aioSCard.getHealthInsurance()==null) {
            	 QRCodeWriter writer = new QRCodeWriter();
 			    
 			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
 			    
 			
 			    	
 			    	  BitMatrix bitMatrix = writer.encode(
 			    			  
 			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
 			    		      "DL act   : "+drivingLicense.getDlNo()			  
 			    			            ,BarcodeFormat.QR_CODE, 350, 350);
 			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
 			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            drivingLicense.setDlStatus("active");
 			aioSCard.setDrivingLicense(drivingLicense);
 			aioSCard.setQrcodePhoto(qrcodePicture);
 			aioSCardRepository.save(aioSCard);
 		    
             }else if(aioSCard.getNationalId()!=null && aioSCard.getHealthInsurance()==null) {
            	 QRCodeWriter writer = new QRCodeWriter();
  			    
  			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
  			    
  			
  			    	
  			    	  BitMatrix bitMatrix = writer.encode(
  			    			  
  			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
  			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
  			    		      "DL act   : "+drivingLicense.getDlNo()			  
  			    			            ,BarcodeFormat.QR_CODE, 350, 350);
  			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
  			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
             
  			drivingLicense.setDlStatus("active");
  			aioSCard.setDrivingLicense(drivingLicense);
  			aioSCard.setQrcodePhoto(qrcodePicture);
  			aioSCardRepository.save(aioSCard);
            	 
             }else if(aioSCard.getNationalId()==null && aioSCard.getHealthInsurance()!=null) {
            	 QRCodeWriter writer = new QRCodeWriter();
   			    
   			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
   			    
   			
   			    	
   			    	  BitMatrix bitMatrix = writer.encode(
   			    			  
   			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
   			    			  "DL act   : "+drivingLicense.getDlNo() +"\n"+
   			    		      "HL No   : "+ healthInsurance.getRssbNo()			  
   			    			            ,BarcodeFormat.QR_CODE, 350, 350);
   			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
   			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            drivingLicense.setDlStatus("active");
   			aioSCard.setDrivingLicense(drivingLicense);
   			aioSCard.setQrcodePhoto(qrcodePicture);
   			aioSCardRepository.save(aioSCard);
             }else if(aioSCard.getNationalId()!=null && aioSCard.getHealthInsurance()!=null) {
            	 QRCodeWriter writer = new QRCodeWriter();
    			    
    			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
    			    
    			
    			    	
    			    	  BitMatrix bitMatrix = writer.encode(
    			    			  
    			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
    			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
    			    			  "DL act     : "+drivingLicense.getDlNo()+"\n"+			  
    			    		      "HL No   : "+ healthInsurance.getRssbNo()			  
    			    			            ,BarcodeFormat.QR_CODE, 350, 350);
    			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
    			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
               drivingLicense.setDlStatus("active");
    			aioSCard.setDrivingLicense(drivingLicense);
    			aioSCard.setQrcodePhoto(qrcodePicture);
    			aioSCardRepository.save(aioSCard);
             }
            
		   
			
			 
		}
        
        
        // END ACTIVE DL
        
        
        
        
        
           public void linkHiService(AioSCard aioSCard,String keyword) throws WriterException, IOException {
			
			aioSCard = aioSCardRepository.findByKeywordUsername(keyword);

		    Citizen citizen = citizenRepository.findAllByUsername(aioSCard.getCitizenUsername());
		    
		     NationalId nationalId = nationalIdRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		     
		     HealthInsurance healthInsurance = healthInsuranceRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		    
		      DrivingLicense drivingLicense = drivingLicenseRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
		    
             if(aioSCard.getNationalId()==null && aioSCard.getDrivingLicense()==null) {
            	 QRCodeWriter writer = new QRCodeWriter();
 			    
 			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
 			    
 			
 			    	
 			    	  BitMatrix bitMatrix = writer.encode(
 			    			  
 			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
 			    		      "HL No   : "+  healthInsurance.getRssbNo()			  
 			    			            ,BarcodeFormat.QR_CODE, 350, 350);
 			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
 			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

 			aioSCard.setHealthInsurance(healthInsurance);
 			aioSCard.setQrcodePhoto(qrcodePicture);
 			aioSCardRepository.save(aioSCard);
 		    
             }else if(aioSCard.getNationalId()!=null && aioSCard.getDrivingLicense()==null) {
            	 QRCodeWriter writer = new QRCodeWriter();
  			    
  			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
  			    
  			
  			    	
  			    	  BitMatrix bitMatrix = writer.encode(
  			    			  
  			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
  			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
  			    		      "HL No   : "+ healthInsurance.getRssbNo()			  
  			    			            ,BarcodeFormat.QR_CODE, 350, 350);
  			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
  			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

  			aioSCard.setHealthInsurance(healthInsurance);
  			aioSCard.setQrcodePhoto(qrcodePicture);
  			aioSCardRepository.save(aioSCard);
            	 
             }else if(aioSCard.getNationalId()==null && aioSCard.getDrivingLicense()!=null) {
            	 QRCodeWriter writer = new QRCodeWriter();
   			    
   			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
   			    
   			
   			    	
   			    	  BitMatrix bitMatrix = writer.encode(
   			    			  
   			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
   			    			  "DL No  : "+ drivingLicense.getDlNo() +"\n"+
   			    		      "HL No   : "+ healthInsurance.getRssbNo()			  
   			    			            ,BarcodeFormat.QR_CODE, 350, 350);
   			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
   			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

   			aioSCard.setHealthInsurance(healthInsurance);
   			aioSCard.setQrcodePhoto(qrcodePicture);
   			aioSCardRepository.save(aioSCard);
             }else if(aioSCard.getNationalId()!=null && aioSCard.getDrivingLicense()!=null) {
            	 QRCodeWriter writer = new QRCodeWriter();
    			    
    			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
    			    
    			
    			    	
    			    	  BitMatrix bitMatrix = writer.encode(
    			    			  
    			    			  "NAME    : "+ citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
    			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
    			    			  "DL No   : "+ drivingLicense.getDlNo()+"\n"+			  
    			    		      "HL No   : "+ healthInsurance.getRssbNo()			  
    			    			            ,BarcodeFormat.QR_CODE, 350, 350);
    			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
    			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    			aioSCard.setHealthInsurance(healthInsurance);
    			aioSCard.setQrcodePhoto(qrcodePicture);
    			aioSCardRepository.save(aioSCard);
             }
            
		   
			
			 
		}
           
           // UNLINK HL
           
           public void unlinkHiService(AioSCard aioSCard,String keyword) throws WriterException, IOException {
   			
   			aioSCard = aioSCardRepository.findByKeywordUsername(keyword);

   		    Citizen citizen = citizenRepository.findAllByUsername(aioSCard.getCitizenUsername());
   		    
   		     NationalId nationalId = nationalIdRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
   		     
   		    HealthInsurance healthInsurance = healthInsuranceRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
   		      DrivingLicense drivingLicense = drivingLicenseRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
   		    
                if(aioSCard.getNationalId()==null && aioSCard.getDrivingLicense()==null) {
               	 QRCodeWriter writer = new QRCodeWriter();
    			    
    			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
    			    
    			
    			    	
    			    	  BitMatrix bitMatrix = writer.encode(
    			    			  
    			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
    			    		      "HL No   : UNLINKED"			  
    			    			            ,BarcodeFormat.QR_CODE, 350, 350);
    			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
    			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    			aioSCard.setHealthInsurance(null);
    			healthInsurance.setLinkStatus("unlinked");
    			aioSCard.setQrcodePhoto(qrcodePicture);
    			aioSCardRepository.save(aioSCard);
    		    
                }else if(aioSCard.getNationalId()!=null && aioSCard.getDrivingLicense()==null) {
               	 QRCodeWriter writer = new QRCodeWriter();
     			    
     			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
     			    
     			
     			    	
     			    	  BitMatrix bitMatrix = writer.encode(
     			    			  
     			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
     			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
     			    		      "HL No   : UNLINKED"			  
     			    			            ,BarcodeFormat.QR_CODE, 350, 350);
     			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
     			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

     			aioSCard.setHealthInsurance(null);
     			healthInsurance.setLinkStatus("unlinked");
     			aioSCard.setQrcodePhoto(qrcodePicture);
     			aioSCardRepository.save(aioSCard);
               	 
                }else if(aioSCard.getNationalId()==null && aioSCard.getDrivingLicense()!=null) {
               	 QRCodeWriter writer = new QRCodeWriter();
      			    
      			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
      			    
      			
      			    	
      			    	  BitMatrix bitMatrix = writer.encode(
      			    			  
      			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
      			    			  "DL No  : "+ drivingLicense.getDlNo() +"\n"+
      			    		      "HL No   : UNLINKED"		  
      			    			            ,BarcodeFormat.QR_CODE, 350, 350);
      			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
      			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

      			aioSCard.setHealthInsurance(null);
      			aioSCard.setQrcodePhoto(qrcodePicture);
      			healthInsurance.setLinkStatus("unlinked");
      			aioSCardRepository.save(aioSCard);
                }else if(aioSCard.getNationalId()!=null && aioSCard.getDrivingLicense()!=null) {
               	 QRCodeWriter writer = new QRCodeWriter();
       			    
       			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
       			    
       			
       			    	
       			    	  BitMatrix bitMatrix = writer.encode(
       			    			  
       			    			  "NAME    : "+ citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
       			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
       			    			  "DL No   : "+ drivingLicense.getDlNo()+"\n"+			  
       			    		      "HL No   : UNLINKED"		  
       			    			            ,BarcodeFormat.QR_CODE, 350, 350);
       			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
       			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

       			aioSCard.setHealthInsurance(null);
       			aioSCard.setQrcodePhoto(qrcodePicture);
       			healthInsurance.setLinkStatus("unlinked");
       			aioSCardRepository.save(aioSCard);
                }
               
   		   
   			
   			 
   		}
           
           
           // END UNLINK
           
           
         // DISABLE HL
           
           public void disableHiService(AioSCard aioSCard,String keyword) throws WriterException, IOException {
   			
   			aioSCard = aioSCardRepository.findByKeywordUsername(keyword);

   		    Citizen citizen = citizenRepository.findAllByUsername(aioSCard.getCitizenUsername());
   		    
   		     NationalId nationalId = nationalIdRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
   		      
		     HealthInsurance healthInsurance = healthInsuranceRepository.findByCitizenUsername(aioSCard.getCitizenUsername());

   		    
   		      DrivingLicense drivingLicense = drivingLicenseRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
   		    
                if(aioSCard.getNationalId()==null && aioSCard.getDrivingLicense()==null) {
               	 QRCodeWriter writer = new QRCodeWriter();
    			    
    			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
    			    
    			
    			    	
    			    	  BitMatrix bitMatrix = writer.encode(
    			    			  
    			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
    			    		      "HL No   : DISABLED"			  
    			    			            ,BarcodeFormat.QR_CODE, 350, 350);
    			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
    			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
                
    			healthInsurance.setHi_status("inactive");    				
    			aioSCard.setHealthInsurance(healthInsurance);
    			aioSCard.setQrcodePhoto(qrcodePicture);
    			aioSCardRepository.save(aioSCard);
    		    
                }else if(aioSCard.getNationalId()!=null && aioSCard.getDrivingLicense()==null) {
               	 QRCodeWriter writer = new QRCodeWriter();
     			    
     			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
     			    
     			
     			    	
     			    	  BitMatrix bitMatrix = writer.encode(
     			    			  
     			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
     			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
     			    		      "HL No   : DISABLED"			  
     			    			            ,BarcodeFormat.QR_CODE, 350, 350);
     			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
     			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
                healthInsurance.setHi_status("inactive");
     			aioSCard.setHealthInsurance(healthInsurance);
     			aioSCard.setQrcodePhoto(qrcodePicture);
     			aioSCardRepository.save(aioSCard);
               	 
                }else if(aioSCard.getNationalId()==null && aioSCard.getDrivingLicense()!=null) {
               	 QRCodeWriter writer = new QRCodeWriter();
      			    
      			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
      			    
      			
      			    	
      			    	  BitMatrix bitMatrix = writer.encode(
      			    			  
      			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
      			    			  "DL No  : "+ drivingLicense.getDlNo() +"\n"+
      			    		      "HL No   : DISABLED"		  
      			    			            ,BarcodeFormat.QR_CODE, 350, 350);
      			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
      			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
                
      			healthInsurance.setHi_status("inactive");    				
      			aioSCard.setHealthInsurance(healthInsurance);
      			aioSCard.setQrcodePhoto(qrcodePicture);
      			aioSCardRepository.save(aioSCard);
                }else if(aioSCard.getNationalId()!=null && aioSCard.getDrivingLicense()!=null) {
               	 QRCodeWriter writer = new QRCodeWriter();
       			    
       			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
       			    
       			
       			    	
       			    	  BitMatrix bitMatrix = writer.encode(
       			    			  
       			    			  "NAME    : "+ citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
       			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
       			    			  "DL No   : "+ drivingLicense.getDlNo()+"\n"+			  
       			    		      "HL No   : DISABLED"		  
       			    			            ,BarcodeFormat.QR_CODE, 350, 350);
       			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
       			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
       			healthInsurance.setHi_status("inactive"); 
       			aioSCard.setHealthInsurance(null);
       			aioSCard.setQrcodePhoto(qrcodePicture);
       			aioSCardRepository.save(aioSCard);
                }
               
   		   
   			
   			 
   		}
           
           
           // END DISABLE HI  
           
           
           
           // ACTIVE HI
           
           
           public void activeHiService(AioSCard aioSCard,String keyword) throws WriterException, IOException {
      			
      			aioSCard = aioSCardRepository.findByKeywordUsername(keyword);

      		    Citizen citizen = citizenRepository.findAllByUsername(aioSCard.getCitizenUsername());
      		    
      		     NationalId nationalId = nationalIdRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
      		     
      		     HealthInsurance healthInsurance = healthInsuranceRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
      		     
      		     DrivingLicense drivingLicense = drivingLicenseRepository.findByCitizenUsername(aioSCard.getCitizenUsername());
      		    
      		    
                   if(aioSCard.getNationalId()==null && aioSCard.getHealthInsurance()==null) {
                  	 QRCodeWriter writer = new QRCodeWriter();
       			    
       			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
       			    
       			
       			    	
       			    	  BitMatrix bitMatrix = writer.encode(
       			    			  
       			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
       			    		      "HL act   : "+healthInsurance.getRssbNo()			  
       			    			            ,BarcodeFormat.QR_CODE, 350, 350);
       			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
       			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
                healthInsurance.setHi_status("active");
                aioSCard.setHealthInsurance(healthInsurance);
       			aioSCard.setQrcodePhoto(qrcodePicture);
       			aioSCardRepository.save(aioSCard);
       		    
                   }else if(aioSCard.getNationalId()!=null && aioSCard.getHealthInsurance()==null) {
                  	 QRCodeWriter writer = new QRCodeWriter();
        			    
        			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
        			    
        			
        			    	
        			    	  BitMatrix bitMatrix = writer.encode(
        			    			  
        			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
        			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
        			    		      "HI act   : "+healthInsurance.getRssbNo()			  
        			    			            ,BarcodeFormat.QR_CODE, 350, 350);
        			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
        			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
                   
        			healthInsurance.setHi_status("active");
        			aioSCard.setHealthInsurance(healthInsurance);
        			aioSCard.setQrcodePhoto(qrcodePicture);
        			aioSCardRepository.save(aioSCard);
                  	 
                   }else if(aioSCard.getNationalId()==null && aioSCard.getHealthInsurance()!=null) {
                  	 QRCodeWriter writer = new QRCodeWriter();
         			    
         			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
         			    
         			
         			    	
         			    	  BitMatrix bitMatrix = writer.encode(
         			    			  
         			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
         			    			  "DL    : "+drivingLicense.getDlNo() +"\n"+
         			    		      "HL act   : "+ healthInsurance.getRssbNo()			  
         			    			            ,BarcodeFormat.QR_CODE, 350, 350);
         			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
         			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
         			healthInsurance.setHi_status("active");
         			aioSCard.setHealthInsurance(healthInsurance);
         			aioSCard.setQrcodePhoto(qrcodePicture);
         			aioSCardRepository.save(aioSCard);
                   }else if(aioSCard.getNationalId()!=null && aioSCard.getHealthInsurance()!=null) {
                  	 QRCodeWriter writer = new QRCodeWriter();
          			    
          			    String qrcodePicture = QRSCANCODEPATH + citizen.getUsername()+"_qr"+".png";
          			    
          			
          			    	
          			    	  BitMatrix bitMatrix = writer.encode(
          			    			  
          			    			  "NAME    : "+  citizen.getFirstname()+" "+citizen.getLastname()+"\n"+
          			    			  "NID No  : "+ nationalId.getNidNo()+"\n"+
          			    			  "DL      : "+drivingLicense.getDlNo()+"\n"+			  
          			    		      "HL act   : "+ healthInsurance.getRssbNo()			  
          			    			            ,BarcodeFormat.QR_CODE, 350, 350);
          			    			    Path path = FileSystems.getDefault().getPath(qrcodePicture);		
          			    				MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
          			healthInsurance.setHi_status("active");
          			aioSCard.setHealthInsurance(healthInsurance);
          			aioSCard.setQrcodePhoto(qrcodePicture);
          			aioSCardRepository.save(aioSCard);
                   }
                  
      		   
      			
      			 
      		}
              
              
              // END ACTIVE HI  
		
		
		public void update(AioSCard aioSCard) {
			aioSCardRepository.save(aioSCard);
		}
		
		public void delete(Integer id) {
			aioSCardRepository.deleteById(id);
		}
		
		// Get aioSCard by keyword
		
		public List<AioSCard> findByKeyword(String keyword){
			return aioSCardRepository.findByKeyword(keyword);
		}	
		
		public void unlinkNid(Integer id) {
			aioSCardRepository.unlinkNid(id);
		}
		
		public void unlinkDL(Integer id) {
			aioSCardRepository.unlinkDL(id);
		}
		public void unlinkHL(Integer id) {
			aioSCardRepository.unlinkHL(id);
		}
		
		
		
		
		
		
}
