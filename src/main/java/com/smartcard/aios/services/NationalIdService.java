package com.smartcard.aios.services;



import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcard.aios.models.Citizen;
import com.smartcard.aios.models.NationalId;
import com.smartcard.aios.repositories.CitizenRepository;
import com.smartcard.aios.repositories.NationalIdRepository;






@Service
@Transactional
public class NationalIdService {
	
	@Autowired
	private NationalIdRepository nationalIdRepository;
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	
	
	//return List of all nationalIds
		public List<NationalId> getNationalIds(){
			return nationalIdRepository.findAll();
		}
		
		public NationalId getNationalId(String citizenUsername) {
			return nationalIdRepository.findByCitizenUsername(citizenUsername);
		}
		
		// save a new nationalId
		public void save(NationalId nationalId) {
			int cat;
			
			Citizen citizen = citizenRepository.findAllByUsername(nationalId.getCitizenUsername());
			
			if(citizen.getCategory().equals("Rwandan") && citizen.getGender().equals("M")&&citizen.getDateOfBirth().getYear()>1940&&citizen.getDateOfBirth().getYear()<1999) {
				cat = 1;
				int M=8;
			    int agec=1;
			    int noINDob = 0;
			    int lastN =0;
			    noINDob = (int)((Math.random() * 9000000)+1000000);
			    lastN = (int)((Math.random() * 90)+10);

				nationalId.setNidNo(""+cat+ " "+citizen.getDateOfBirth().getYear()+" "+M+" "+noINDob+" "+agec+" "+lastN);
			
				nationalId.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
				nationalId.setOwnerGender(citizen.getGender());
				nationalId.setOwnerDob(citizen.getDateOfBirth());
				nationalId.setPhoto(citizen.getPhoto());
				nationalId.setCitizen(citizen);
				nationalIdRepository.save(nationalId);	
			}else if(citizen.getCategory().equals("Rwandan") && citizen.getGender().equals("F")&&citizen.getDateOfBirth().getYear()>1940&&citizen.getDateOfBirth().getYear()<1999) {
				cat = 1;
				int F=7;
			    int agec=1;
			    int noINDob = 0;
			    int lastN =0;
			    noINDob = (int)((Math.random() * 9000000)+1000000);
			    lastN = (int)((Math.random() * 90)+10);

				nationalId.setNidNo(""+cat+ " "+citizen.getDateOfBirth().getYear()+" "+F+" "+noINDob+" "+agec+" "+lastN);
			
				nationalId.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
				nationalId.setOwnerGender(citizen.getGender());
				nationalId.setOwnerDob(citizen.getDateOfBirth());
				nationalId.setPhoto(citizen.getPhoto());
				nationalId.setCitizen(citizen);
				nationalIdRepository.save(nationalId);	
			}else if(citizen.getCategory().equals("Rwandan") && citizen.getGender().equals("M")&&citizen.getDateOfBirth().getYear()>2000&&citizen.getDateOfBirth().getYear()<=2020) {
				cat = 1;
				int M=8;
			    int agec=2;
			    int noINDob = 0;
			    int lastN =0;
			    noINDob = (int)((Math.random() * 9000000)+1000000);
			    lastN = (int)((Math.random() * 90)+10);

				nationalId.setNidNo(""+cat+ " "+citizen.getDateOfBirth().getYear()+" "+M+" "+noINDob+" "+agec+" "+lastN);
			
				nationalId.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
				nationalId.setOwnerGender(citizen.getGender());
				nationalId.setOwnerDob(citizen.getDateOfBirth());
				nationalId.setPhoto(citizen.getPhoto());
				nationalId.setCitizen(citizen);
				nationalIdRepository.save(nationalId);	
			}else if(citizen.getCategory().equals("Rwandan") && citizen.getGender().equals("F")&&citizen.getDateOfBirth().getYear()>2000&&citizen.getDateOfBirth().getYear()<=2020) {
				cat = 1;
				int F=7;
			    int agec=2;
			    int noINDob = 0;
			    int lastN =0;
			    noINDob = (int)((Math.random() * 9000000)+1000000);
			    lastN = (int)((Math.random() * 90)+10);

				nationalId.setNidNo(""+cat+ " "+citizen.getDateOfBirth().getYear()+" "+F+" "+noINDob+" "+agec+" "+lastN);
			
				nationalId.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
				nationalId.setOwnerGender(citizen.getGender());
				nationalId.setOwnerDob(citizen.getDateOfBirth());
				nationalId.setPhoto(citizen.getPhoto());
				nationalId.setCitizen(citizen);
				nationalIdRepository.save(nationalId);	
			}
			
			//Refugee
			
			
			if(citizen.getCategory().equals("Refugees") && citizen.getGender().equals("M")&&citizen.getDateOfBirth().getYear()>1940&&citizen.getDateOfBirth().getYear()<1999) {
				cat = 3;
				int M=8;
			    int agec=1;
			    int noINDob = 0;
			    int lastN =0;
			    noINDob = (int)((Math.random() * 9000000)+1000000);
			    lastN = (int)((Math.random() * 90)+10);

				nationalId.setNidNo(""+cat+ " "+citizen.getDateOfBirth().getYear()+" "+M+" "+noINDob+" "+agec+" "+lastN);
			
				nationalId.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
				nationalId.setOwnerGender(citizen.getGender());
				nationalId.setOwnerDob(citizen.getDateOfBirth());
				nationalId.setPhoto(citizen.getPhoto());
				nationalId.setCitizen(citizen);
				nationalIdRepository.save(nationalId);	
			}else if(citizen.getCategory().equals("Refugees") && citizen.getGender().equals("F")&&citizen.getDateOfBirth().getYear()>1940&&citizen.getDateOfBirth().getYear()<1999) {
				cat = 3;
				int F=7;
			    int agec=1;
			    int noINDob = 0;
			    int lastN =0;
			    noINDob = (int)((Math.random() * 9000000)+1000000);
			    lastN = (int)((Math.random() * 90)+10);

				nationalId.setNidNo(""+cat+ " "+citizen.getDateOfBirth().getYear()+" "+F+" "+noINDob+" "+agec+" "+lastN);
			
				nationalId.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
				nationalId.setOwnerGender(citizen.getGender());
				nationalId.setOwnerDob(citizen.getDateOfBirth());
				nationalId.setPhoto(citizen.getPhoto());
				nationalId.setCitizen(citizen);
				nationalIdRepository.save(nationalId);	
			}else if(citizen.getCategory().equals("Refugees") && citizen.getGender().equals("M")&&citizen.getDateOfBirth().getYear()>2000&&citizen.getDateOfBirth().getYear()<=2020) {
				cat = 3;
				int M=8;
			    int agec=2;
			    int noINDob = 0;
			    int lastN =0;
			    noINDob = (int)((Math.random() * 9000000)+1000000);
			    lastN = (int)((Math.random() * 90)+10);

				nationalId.setNidNo(""+cat+ " "+citizen.getDateOfBirth().getYear()+" "+M+" "+noINDob+" "+agec+" "+lastN);
			
				nationalId.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
				nationalId.setOwnerGender(citizen.getGender());
				nationalId.setOwnerDob(citizen.getDateOfBirth());
				nationalId.setPhoto(citizen.getPhoto());
				nationalId.setCitizen(citizen);
				nationalIdRepository.save(nationalId);	
			}else if(citizen.getCategory().equals("Refugees") && citizen.getGender().equals("F")&&citizen.getDateOfBirth().getYear()>2000&&citizen.getDateOfBirth().getYear()<=2020) {
				cat = 3;
				int F=7;
			    int agec=2;
			    int noINDob = 0;
			    int lastN =0;
			    noINDob = (int)((Math.random() * 9000000)+1000000);
			    lastN = (int)((Math.random() * 90)+10);

				nationalId.setNidNo(""+cat+ " "+citizen.getDateOfBirth().getYear()+" "+F+" "+noINDob+" "+agec+" "+lastN);
			
				nationalId.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
				nationalId.setOwnerGender(citizen.getGender());
				nationalId.setOwnerDob(citizen.getDateOfBirth());
				nationalId.setPhoto(citizen.getPhoto());
				nationalId.setCitizen(citizen);
				nationalIdRepository.save(nationalId);	
			}
			
			
			
			
			//FOREIGN
			
			
			if(citizen.getCategory().equals("Foreigner") && citizen.getGender().equals("M")&&citizen.getDateOfBirth().getYear()>1940&&citizen.getDateOfBirth().getYear()<1999) {
				cat = 2;
				int M=8;
			    int agec=1;
			    int noINDob = 0;
			    int lastN =0;
			    noINDob = (int)((Math.random() * 9000000)+1000000);
			    lastN = (int)((Math.random() * 90)+10);

				nationalId.setNidNo(""+cat+ " "+citizen.getDateOfBirth().getYear()+" "+M+" "+noINDob+" "+agec+" "+lastN);
			
				nationalId.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
				nationalId.setOwnerGender(citizen.getGender());
				nationalId.setOwnerDob(citizen.getDateOfBirth());
				nationalId.setPhoto(citizen.getPhoto());
				nationalId.setCitizen(citizen);
				nationalIdRepository.save(nationalId);	
			}else if(citizen.getCategory().equals("Foreigner") && citizen.getGender().equals("F")&&citizen.getDateOfBirth().getYear()>1940&&citizen.getDateOfBirth().getYear()<1999) {
				cat = 2;
				int F=7;
			    int agec=1;
			    int noINDob = 0;
			    int lastN =0;
			    noINDob = (int)((Math.random() * 9000000)+1000000);
			    lastN = (int)((Math.random() * 90)+10);

				nationalId.setNidNo(""+cat+ " "+citizen.getDateOfBirth().getYear()+" "+F+" "+noINDob+" "+agec+" "+lastN);
			
				nationalId.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
				nationalId.setOwnerGender(citizen.getGender());
				nationalId.setOwnerDob(citizen.getDateOfBirth());
				nationalId.setPhoto(citizen.getPhoto());
				nationalId.setCitizen(citizen);
				nationalIdRepository.save(nationalId);	
			}else if(citizen.getCategory().equals("Foreigner") && citizen.getGender().equals("M")&&citizen.getDateOfBirth().getYear()>2000&&citizen.getDateOfBirth().getYear()<=2020) {
				cat = 2;
				int M=8;
			    int agec=2;
			    int noINDob = 0;
			    int lastN =0;
			    noINDob = (int)((Math.random() * 9000000)+1000000);
			    lastN = (int)((Math.random() * 90)+10);

				nationalId.setNidNo(""+cat+ " "+citizen.getDateOfBirth().getYear()+" "+M+" "+noINDob+" "+agec+" "+lastN);
			
				nationalId.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
				nationalId.setOwnerGender(citizen.getGender());
				nationalId.setOwnerDob(citizen.getDateOfBirth());
				nationalId.setPhoto(citizen.getPhoto());
				nationalId.setCitizen(citizen);
				nationalIdRepository.save(nationalId);	
			}else if(citizen.getCategory().equals("Foreigner") && citizen.getGender().equals("F")&&citizen.getDateOfBirth().getYear()>2000&&citizen.getDateOfBirth().getYear()<=2020) {
				cat = 2;
				int F=7;
			    int agec=2;
			    int noINDob = 0;
			    int lastN =0;
			    noINDob = (int)((Math.random() * 9000000)+1000000);
			    lastN = (int)((Math.random() * 90)+10);

				nationalId.setNidNo(""+cat+ " "+citizen.getDateOfBirth().getYear()+" "+F+" "+noINDob+" "+agec+" "+lastN);
			
				nationalId.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
				nationalId.setOwnerGender(citizen.getGender());
				nationalId.setOwnerDob(citizen.getDateOfBirth());
				nationalId.setPhoto(citizen.getPhoto());
				nationalId.setCitizen(citizen);
				nationalIdRepository.save(nationalId);	
			}
			
		
			
		}
		
		// find a nationalId by id
		
		public Optional<NationalId> findById (Integer id) {
			return nationalIdRepository.findById(id);

		}
		
		public void update(NationalId nationalId) {
			nationalIdRepository.save(nationalId);
		}
		
		public void delete(Integer id) {
			nationalIdRepository.deleteById(id);
		}
		
		// Get nationalId by keyword
		
		public List<NationalId> findByKeyword(String keyword){
			return nationalIdRepository.findByKeyowrd(keyword);
		}		
		
		
}
