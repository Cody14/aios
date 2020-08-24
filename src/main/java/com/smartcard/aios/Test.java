package com.smartcard.aios;

import com.smartcard.aios.models.AioSCard;
import com.smartcard.aios.models.Citizen;
import com.smartcard.aios.models.District;
import com.smartcard.aios.models.NationalId;

public class Test {

	public static void main(String[] args) {
		
		AioSCard aioSCard = new AioSCard();
		
		NationalId nationalId = new NationalId();
		
		Citizen citizen = new Citizen();
		

		
		
		
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
		
		
		
		
		System.out.println("AIO SMART CARD "+aioSCard.getCitizen().getFirstname() + " National Id No "+aioSCard.getNationalId().getNidNo() );
		

	}

}
