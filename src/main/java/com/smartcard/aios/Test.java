package com.smartcard.aios;

import com.smartcard.aios.models.AioSCard;
import com.smartcard.aios.models.Citizen;
import com.smartcard.aios.models.District;
import com.smartcard.aios.models.NationalId;
import com.smartcard.aios.models.Village;

public class Test {

	public static void main(String[] args) {
		
		AioSCard aioSCard = new AioSCard();
		
		NationalId nationalId = new NationalId();
		
		Citizen citizen = new Citizen();
		

		
		Village village = new Village();
		
		village.setId(1);
		village.setVillageName("Rukiri");
		
		citizen.setCurrentPlace(village);
		
		citizen.setId(1);
		citizen.setFirstname("Fiona");
		citizen.setLastname("Gallager");
		
		nationalId.setCitizen(citizen);
		nationalId.setId(1);
		nationalId.setNidNo("1 1994 7 231231 1 23");
		nationalId.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
		
		aioSCard.setAioscardNo("1233 2311 2321 4411");
		aioSCard.setNationalId(nationalId);
		aioSCard.setCitizen(citizen);
		
		
		
		//System.out.println("VILLAGE NAME : "+citizen.getCurrentPlace().getVillageName());
		
		
		System.out.println("AIO SMART CARD "+aioSCard.getCitizen().getFirstname() + " National Id No "+aioSCard.getNationalId().getNidNo() );
		

	}

}
