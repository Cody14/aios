package com.smartcard.aios.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcard.aios.models.Citizen;
import com.smartcard.aios.models.DrivingLicense;
import com.smartcard.aios.repositories.CitizenRepository;
import com.smartcard.aios.repositories.DrivingLicenseRepository;



@Service
public class DrivingLicenseService {

	@Autowired
	private DrivingLicenseRepository drivingLicenseRepository;
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	//return List of all drivingLicenses
		public List<DrivingLicense> getDrivingLicenses(){
			return drivingLicenseRepository.findAll();
		}
		
		
		public DrivingLicense getDrivingLicense(String citizenUsername) {
			return drivingLicenseRepository.findByCitizenUsername(citizenUsername);
		}
		
		
		public DrivingLicense addCategory_A(String keyword) {
			return drivingLicenseRepository.findByUsername(keyword);
		}
		
		
		public DrivingLicense addCategory_C(String keyword) {
			return drivingLicenseRepository.findByUsername(keyword);
		}
		
		public DrivingLicense addCategory_D(String keyword) {
			return drivingLicenseRepository.findByUsername(keyword);
		}
		
		
		
		
		
		// save a new drivingLicense
		public void save(DrivingLicense drivingLicense) {
		    LocalDate Dt;
			int cat;
			int g;
			int agec;
			int noINDob = 0;
		    int lastN =0;
		    noINDob = (int)((Math.random() * 9000000)+1000000);
			lastN = (int)((Math.random() * 90)+10);
			
		   Citizen citizen = citizenRepository.findAllByUsername(drivingLicense.getCitizenUsername());
		   
		   if(citizen.getCategory().equals("Rwandan") && citizen.getGender().equals("M")&& citizen.getDateOfBirth().getYear()>1940&&citizen.getDateOfBirth().getYear()<1999) {
			   cat =1;
			   g= 8;
			   agec=1;
			   
			   drivingLicense.setCitizenUsername(citizen.getUsername());
			   drivingLicense.setOwnerGender(citizen.getGender());
			   drivingLicense.setOwnerDob(citizen.getDateOfBirth());
			   drivingLicense.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
			   drivingLicense.setDlNo(cat+" "+citizen.getDateOfBirth().getYear()+" "+g+" "+noINDob+" "+agec+" "+lastN);

//			   drivingLicense.setExpireDate(drivingLicense.getIssuedDate().plusYears(10));
			   drivingLicense.setCitizen(citizen);
			   drivingLicenseRepository.save(drivingLicense);
		   }else if(citizen.getCategory().equals("Rwandan") && citizen.getGender().equals("F")&& citizen.getDateOfBirth().getYear()>1940&&citizen.getDateOfBirth().getYear()<1999) {
			   cat =1;
			   g= 7;
			   agec=1;
			   
			   drivingLicense.setCitizenUsername(citizen.getUsername());
			   drivingLicense.setOwnerGender(citizen.getGender());
			   drivingLicense.setOwnerDob(citizen.getDateOfBirth());
			   drivingLicense.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
			   drivingLicense.setDlNo(cat+" "+citizen.getDateOfBirth().getYear()+" "+g+" "+noINDob+" "+agec+" "+lastN);  
	
			  // drivingLicense.setExpireDate(drivingLicense.getIssuedDate().plusYears(10));
			   drivingLicense.setCitizen(citizen);
			   drivingLicenseRepository.save(drivingLicense);
		   }else if(citizen.getCategory().equals("Rwandan") && citizen.getGender().equals("M")&& citizen.getDateOfBirth().getYear()>20000&&citizen.getDateOfBirth().getYear()<=2020) {
			   cat =1;
			   g= 8;
			   agec=2;
			   
			   drivingLicense.setCitizenUsername(citizen.getUsername());
			   drivingLicense.setOwnerGender(citizen.getGender());
			   drivingLicense.setOwnerDob(citizen.getDateOfBirth());
			   drivingLicense.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
			   drivingLicense.setDlNo(cat+" "+citizen.getDateOfBirth().getYear()+" "+g+" "+noINDob+" "+agec+" "+lastN);
			  
		
			  // drivingLicense.setExpireDate(drivingLicense.getIssuedDate().plusYears(10));
			   drivingLicense.setCitizen(citizen);
			   drivingLicenseRepository.save(drivingLicense);
		   }else if(citizen.getCategory().equals("Rwandan") && citizen.getGender().equals("F")&& citizen.getDateOfBirth().getYear()>20000&&citizen.getDateOfBirth().getYear()<=2020) {
			   cat =1;
			   g= 7;
			   agec=2;
			   
			   drivingLicense.setCitizenUsername(citizen.getUsername());
			   drivingLicense.setOwnerGender(citizen.getGender());
			   drivingLicense.setOwnerDob(citizen.getDateOfBirth());
			   drivingLicense.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
			   drivingLicense.setDlNo(cat+" "+citizen.getDateOfBirth().getYear()+" "+g+" "+noINDob+" "+agec+" "+lastN);
			  
		
			  // drivingLicense.setExpireDate(drivingLicense.getIssuedDate().plusYears(10));
			   drivingLicense.setCitizen(citizen);
			   drivingLicenseRepository.save(drivingLicense);
		   }
		   
		   if(citizen.getCategory().equals("Refugees") && citizen.getGender().equals("M")&& citizen.getDateOfBirth().getYear()>1940&&citizen.getDateOfBirth().getYear()<1999) {
			   cat =3;
			   g= 8;
			   agec=1;
			   
			   drivingLicense.setCitizenUsername(citizen.getUsername());
			   drivingLicense.setOwnerGender(citizen.getGender());
			   drivingLicense.setOwnerDob(citizen.getDateOfBirth());
			   drivingLicense.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
			   drivingLicense.setDlNo(cat+" "+citizen.getDateOfBirth().getYear()+" "+g+" "+noINDob+" "+agec+" "+lastN);
		
			 //  drivingLicense.setExpireDate(drivingLicense.getIssuedDate().plusYears(10));
			   drivingLicense.setCitizen(citizen);
			   drivingLicenseRepository.save(drivingLicense);
		   }else if(citizen.getCategory().equals("Refugees") && citizen.getGender().equals("F")&& citizen.getDateOfBirth().getYear()>1940&&citizen.getDateOfBirth().getYear()<1999) {
			   cat =3;
			   g= 7;
			   agec=1;
			   
			   drivingLicense.setCitizenUsername(citizen.getUsername());
			   drivingLicense.setOwnerGender(citizen.getGender());
			   drivingLicense.setOwnerDob(citizen.getDateOfBirth());
			   drivingLicense.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
			   drivingLicense.setDlNo(cat+" "+citizen.getDateOfBirth().getYear()+" "+g+" "+noINDob+" "+agec+" "+lastN);
			   
	
			 //  drivingLicense.setExpireDate(drivingLicense.getIssuedDate().plusYears(10));
			   drivingLicense.setCitizen(citizen);
			   drivingLicenseRepository.save(drivingLicense);
		   }else if(citizen.getCategory().equals("Refugees") && citizen.getGender().equals("M")&& citizen.getDateOfBirth().getYear()>20000&&citizen.getDateOfBirth().getYear()<=2020) {
			   cat =3;
			   g= 8;
			   agec=2;
			   
			   drivingLicense.setCitizenUsername(citizen.getUsername());
			   drivingLicense.setOwnerGender(citizen.getGender());
			   drivingLicense.setOwnerDob(citizen.getDateOfBirth());
			   drivingLicense.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
			   drivingLicense.setDlNo(cat+" "+citizen.getDateOfBirth().getYear()+" "+g+" "+noINDob+" "+agec+" "+lastN);

			 //  drivingLicense.setExpireDate(drivingLicense.getIssuedDate().plusYears(10));
			   drivingLicense.setCitizen(citizen);
			   drivingLicenseRepository.save(drivingLicense);
		   }else if(citizen.getCategory().equals("Refugees") && citizen.getGender().equals("F")&& citizen.getDateOfBirth().getYear()>20000&&citizen.getDateOfBirth().getYear()<=2020) {
			   cat =3;
			   g= 7;
			   agec=2;
			   
			   drivingLicense.setCitizenUsername(citizen.getUsername());
			   drivingLicense.setOwnerGender(citizen.getGender());
			   drivingLicense.setOwnerDob(citizen.getDateOfBirth());
			   drivingLicense.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
			   drivingLicense.setDlNo(cat+" "+citizen.getDateOfBirth().getYear()+" "+g+" "+noINDob+" "+agec+" "+lastN);


			//   drivingLicense.setExpireDate(drivingLicense.getIssuedDate().plusYears(10));
			   drivingLicense.setCitizen(citizen);
			   drivingLicenseRepository.save(drivingLicense);
		   }
		   
		   if(citizen.getCategory().equals("Foreigner") && citizen.getGender().equals("M")&& citizen.getDateOfBirth().getYear()>1940&&citizen.getDateOfBirth().getYear()<1999) {
			   cat =2;
			   g= 8;
			   agec=1;
			   
			   drivingLicense.setCitizenUsername(citizen.getUsername());
			   drivingLicense.setOwnerGender(citizen.getGender());
			   drivingLicense.setOwnerDob(citizen.getDateOfBirth());
			   drivingLicense.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
			   drivingLicense.setDlNo(cat+" "+citizen.getDateOfBirth().getYear()+" "+g+" "+noINDob+" "+agec+" "+lastN);

			 //  drivingLicense.setExpireDate(drivingLicense.getIssuedDate().plusYears(10));
			   drivingLicense.setCitizen(citizen);
			   drivingLicenseRepository.save(drivingLicense);
		   }else if(citizen.getCategory().equals("Foreigner") && citizen.getGender().equals("F")&& citizen.getDateOfBirth().getYear()>1940&&citizen.getDateOfBirth().getYear()<1999) {
			   cat =1;
			   g= 7;
			   agec=1;
			   
			   drivingLicense.setCitizenUsername(citizen.getUsername());
			   drivingLicense.setOwnerGender(citizen.getGender());
			   drivingLicense.setOwnerDob(citizen.getDateOfBirth());
			   drivingLicense.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
			   drivingLicense.setDlNo(cat+" "+citizen.getDateOfBirth().getYear()+" "+g+" "+noINDob+" "+agec+" "+lastN);

			 //  drivingLicense.setExpireDate(drivingLicense.getIssuedDate().plusYears(10));
			   drivingLicense.setCitizen(citizen);
			   drivingLicenseRepository.save(drivingLicense);
		   }else if(citizen.getCategory().equals("Foreigner") && citizen.getGender().equals("M")&& citizen.getDateOfBirth().getYear()>20000&&citizen.getDateOfBirth().getYear()<=2020) {
			   cat =2;
			   g= 8;
			   agec=2;
			   
			   drivingLicense.setCitizenUsername(citizen.getUsername());
			   drivingLicense.setOwnerGender(citizen.getGender());
			   drivingLicense.setOwnerDob(citizen.getDateOfBirth());
			   drivingLicense.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
			   drivingLicense.setDlNo(cat+" "+citizen.getDateOfBirth().getYear()+" "+g+" "+noINDob+" "+agec+" "+lastN);

	
			 //  drivingLicense.setExpireDate(drivingLicense.getIssuedDate().plusYears(10));
			   drivingLicense.setCitizen(citizen);
			   drivingLicenseRepository.save(drivingLicense);
		   }else if(citizen.getCategory().equals("Foreigner") && citizen.getGender().equals("F")&& citizen.getDateOfBirth().getYear()>20000&&citizen.getDateOfBirth().getYear()<=2020) {
			   cat =2;
			   g= 7;
			   agec=2;
			   
			   drivingLicense.setCitizenUsername(citizen.getUsername());
			   drivingLicense.setOwnerGender(citizen.getGender());
			   drivingLicense.setOwnerDob(citizen.getDateOfBirth());
			   drivingLicense.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
			   drivingLicense.setDlNo(cat+" "+citizen.getDateOfBirth().getYear()+" "+g+" "+noINDob+" "+agec+" "+lastN);
			   
			  
			   
			 //  drivingLicense.setExpireDate(drivingLicense.getIssuedDate().plusYears(10));
			   drivingLicense.setCitizen(citizen);
			   drivingLicenseRepository.save(drivingLicense);
		   }
		   
		  
		  
		   
			
			
			
			
		}
		
		// find a drivingLicense by id
		
		public Optional<DrivingLicense> findById (Integer id) {
			return drivingLicenseRepository.findById(id);

		}
		
		public void update(DrivingLicense drivingLicense) {
			drivingLicenseRepository.save(drivingLicense);
		}
		
		public void updateCatA(DrivingLicense drivingLicense) {
			drivingLicenseRepository.save(drivingLicense);
		}
		
		public void delete(Integer id) {
			drivingLicenseRepository.deleteById(id);
		}
		
		public List<DrivingLicense> findByKeyword(String keyword){
			return drivingLicenseRepository.findByKeyowrd(keyword);
		}
}
