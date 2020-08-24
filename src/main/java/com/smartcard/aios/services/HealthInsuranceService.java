package com.smartcard.aios.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcard.aios.models.Citizen;
import com.smartcard.aios.models.HealthInsurance;
import com.smartcard.aios.repositories.CitizenRepository;
import com.smartcard.aios.repositories.HealthInsuranceRepository;



@Service
public class HealthInsuranceService {

	@Autowired
	private HealthInsuranceRepository healthInsuranceRepository;
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	//return List of all healthInsurances
		public List<HealthInsurance> getHealthInsurances(){
			return healthInsuranceRepository.findAll();
		}
		
		
		
		public HealthInsurance getHealthInsurance(String citizenUsername) {
			return healthInsuranceRepository.findByCitizenUsername(citizenUsername);
		}
		
		// save a new healthInsurance
		public void save(HealthInsurance healthInsurance) {
			
			Citizen citizen = citizenRepository.findAllByUsername(healthInsurance.getCitizenUsername());
			
			
			
			
			int cat,g,ag;
			int year = LocalDate.now().getYear();
			int ad= year - citizen.getDateOfBirth().getYear();
			String category = citizen.getCategory();
			String gender = citizen.getGender();

			
			int rssbNo = (int)((Math.random() * 900000)+100000);
			if(category.equals("Rwandan") && gender.equals("M")&& ad<=30 ) {
			    cat=0;
				g=0;
				ag=1;
				
				
				
				healthInsurance.setOwnerName(citizen.getFirstname()+ " "+citizen.getLastname());
				healthInsurance.setOwnerDob(citizen.getDateOfBirth());
				healthInsurance.setOwnerGender(citizen.getGender());

				healthInsurance.setPhoto(citizen.getPhoto());
				healthInsurance.setCitizenUsername(citizen.getUsername());
				healthInsurance.setCitizen(citizen);
				healthInsurance.setRssbNo(""+cat+""+g+""+ag+""+rssbNo);
				healthInsuranceRepository.save(healthInsurance);
				
			}
			
			else if(category.equals("Rwandan") && gender.equals("M")&& ad>=30) {
			    cat=0;
				g=0;
				ag=0;
				
				
				healthInsurance.setOwnerName(citizen.getFirstname()+ " "+citizen.getLastname());
				healthInsurance.setOwnerDob(citizen.getDateOfBirth());
				healthInsurance.setOwnerGender(citizen.getGender());
				healthInsurance.setPhoto(citizen.getPhoto());
				healthInsurance.setCitizenUsername(citizen.getUsername());
				healthInsurance.setCitizen(citizen);
				healthInsurance.setRssbNo(""+cat+""+g+""+ag+""+rssbNo);
				healthInsuranceRepository.save(healthInsurance);
			}else if(category.equals("Rwandan") && gender.equals("F")&& ad<=30) {
				cat=0;
				g=1;
				ag=1;
				
				
				healthInsurance.setOwnerName(citizen.getFirstname()+ " "+citizen.getLastname());
				healthInsurance.setOwnerDob(citizen.getDateOfBirth());
				healthInsurance.setOwnerGender(citizen.getGender());
				healthInsurance.setPhoto(citizen.getPhoto());
				healthInsurance.setCitizenUsername(citizen.getUsername());
				healthInsurance.setCitizen(citizen);
				healthInsurance.setRssbNo(""+cat+""+g+""+ag+""+rssbNo);
				healthInsuranceRepository.save(healthInsurance);
			}else if(category.equals("Rwandan") && gender.equals("F")&& ad>=30) {
				cat=0;
				g=1;
				ag=0;
				
				
				healthInsurance.setOwnerName(citizen.getFirstname()+ " "+citizen.getLastname());
				healthInsurance.setOwnerDob(citizen.getDateOfBirth());
				healthInsurance.setOwnerGender(citizen.getGender());

				healthInsurance.setPhoto(citizen.getPhoto());
				healthInsurance.setCitizenUsername(citizen.getUsername());
				healthInsurance.setCitizen(citizen);
				healthInsurance.setRssbNo(""+cat+""+g+""+ag+""+rssbNo);
				healthInsuranceRepository.save(healthInsurance);
			}
			
			
			else if(category.equals("Foreigner") && gender.equals("M")&& ad<=30 ) {
			    cat=2;
				g=0;
				ag=1;
				
				
				healthInsurance.setOwnerName(citizen.getFirstname()+ " "+citizen.getLastname());
				healthInsurance.setOwnerDob(citizen.getDateOfBirth());
				healthInsurance.setOwnerGender(citizen.getGender());

				healthInsurance.setPhoto(citizen.getPhoto());
				healthInsurance.setCitizenUsername(citizen.getUsername());
				healthInsurance.setCitizen(citizen);
				healthInsurance.setRssbNo(""+cat+""+g+""+ag+""+rssbNo);
				healthInsuranceRepository.save(healthInsurance);
				
			}else if(category.equals("Foreigner") && gender.equals("M")&& ad>=30) {
			    cat=2;
				g=0;
				ag=0;
				
				
				healthInsurance.setOwnerName(citizen.getFirstname()+ " "+citizen.getLastname());
				healthInsurance.setOwnerDob(citizen.getDateOfBirth());
				healthInsurance.setOwnerGender(citizen.getGender());

				healthInsurance.setPhoto(citizen.getPhoto());
				healthInsurance.setCitizenUsername(citizen.getUsername());
				healthInsurance.setCitizen(citizen);
				healthInsurance.setRssbNo(""+cat+""+g+""+ag+""+rssbNo);
				healthInsuranceRepository.save(healthInsurance);
			}else if(category.equals("Foreigner") && gender.equals("F")&& ad<=30) {
				cat=2;
				g=1;
				ag=1;
				
				
				healthInsurance.setOwnerName(citizen.getFirstname()+ " "+citizen.getLastname());
				healthInsurance.setOwnerDob(citizen.getDateOfBirth());
				healthInsurance.setOwnerGender(citizen.getGender());

				healthInsurance.setPhoto(citizen.getPhoto());
				healthInsurance.setCitizenUsername(citizen.getUsername());
				healthInsurance.setCitizen(citizen);
				healthInsurance.setRssbNo(""+cat+""+g+""+ag+""+rssbNo);
				healthInsuranceRepository.save(healthInsurance);
			}else if(category.equals("Foreigner") && gender.equals("F")&& ad>=30) {
				cat=2;
				g=1;
				ag=0;
				
				
				healthInsurance.setOwnerName(citizen.getFirstname()+ " "+citizen.getLastname());
				healthInsurance.setOwnerDob(citizen.getDateOfBirth());
				healthInsurance.setOwnerGender(citizen.getGender());

				healthInsurance.setPhoto(citizen.getPhoto());
				healthInsurance.setCitizenUsername(citizen.getUsername());
				healthInsurance.setCitizen(citizen);
				healthInsurance.setRssbNo(""+cat+""+g+""+ag+""+rssbNo);
				healthInsuranceRepository.save(healthInsurance);
			}
			
			else if(category.equals("Refugees") && gender.equals("M")&& ad<=30 ) {
			    cat=1;
				g=0;
				ag=1;
				
				
				healthInsurance.setOwnerName(citizen.getFirstname()+ " "+citizen.getLastname());
				healthInsurance.setOwnerDob(citizen.getDateOfBirth());
				healthInsurance.setOwnerGender(citizen.getGender());

				healthInsurance.setPhoto(citizen.getPhoto());
				healthInsurance.setCitizenUsername(citizen.getUsername());
				healthInsurance.setCitizen(citizen);
				healthInsurance.setRssbNo(""+cat+""+g+""+ag+""+rssbNo);
				healthInsuranceRepository.save(healthInsurance);
				
			}else if(category.equals("Refugees") && gender.equals("M")&& ad>=30) {
			    cat=1;
				g=0;
				ag=0;
				
				
				healthInsurance.setOwnerName(citizen.getFirstname()+ " "+citizen.getLastname());
				healthInsurance.setOwnerDob(citizen.getDateOfBirth());
				healthInsurance.setOwnerGender(citizen.getGender());

				healthInsurance.setPhoto(citizen.getPhoto());
				healthInsurance.setCitizenUsername(citizen.getUsername());
				healthInsurance.setCitizen(citizen);
				healthInsurance.setRssbNo(""+cat+""+g+""+ag+""+rssbNo);
				healthInsuranceRepository.save(healthInsurance);
			}else if(category.equals("Refugees") && gender.equals("F")&& ad<=30) {
				cat=1;
				g=1;
				ag=1;
				
				
				healthInsurance.setOwnerName(citizen.getFirstname()+ " "+citizen.getLastname());
				healthInsurance.setOwnerDob(citizen.getDateOfBirth());
				healthInsurance.setOwnerGender(citizen.getGender());

				healthInsurance.setPhoto(citizen.getPhoto());
				healthInsurance.setCitizenUsername(citizen.getUsername());
				healthInsurance.setCitizen(citizen);
				healthInsurance.setRssbNo(""+cat+""+g+""+ag+""+rssbNo);
				healthInsuranceRepository.save(healthInsurance);
			}else if(category.equals("Refugees") && gender.equals("F")&& ad>=30) {
				cat=1;
				g=1;
				ag=0;
				
				
				healthInsurance.setOwnerName(citizen.getFirstname()+ " "+citizen.getLastname());
				healthInsurance.setOwnerDob(citizen.getDateOfBirth());
				healthInsurance.setOwnerGender(citizen.getGender());

				healthInsurance.setPhoto(citizen.getPhoto());
				healthInsurance.setCitizenUsername(citizen.getUsername());
				healthInsurance.setCitizen(citizen);
				healthInsurance.setRssbNo(""+cat+""+g+""+ag+""+rssbNo);
				healthInsuranceRepository.save(healthInsurance);
			}
			
			
		}
		
		// find a healthInsurance by id
		
		public Optional<HealthInsurance> findById (Integer id) {
			return healthInsuranceRepository.findById(id);

		}
		
		public void update(HealthInsurance healthInsurance) {
			healthInsuranceRepository.save(healthInsurance);
		}
		
		public void delete(Integer id) {
			healthInsuranceRepository.deleteById(id);
		}
		
		public List<HealthInsurance> findByKeyword(String keyword){
			return healthInsuranceRepository.findByKeyowrd(keyword);
		}
}
