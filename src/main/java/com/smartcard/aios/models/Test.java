package com.smartcard.aios.models;

import java.time.Year;

public class Test {

	public static void main(String[] args) {
		
		Citizen citizen = new Citizen();
		
		NationalId nationalId = new NationalId();
		
		AioSCard aioSCard = new AioSCard();
		
		Province province = new Province();
		
		District district = new District();
		
		Sector sector = new Sector();
		
		province.setId(1);
		province.setProvinceName("South");
		
		district.setId(2);
		district.setDistrictName("Muhanga");
		district.setProvince(province);
		
		sector.setId(3);
		sector.setSectorName("Muh Sector");
		sector.setDistrict(district);
		
		
		
		
	   citizen.setId(1);
	   citizen.setFirstname("Gasana");
	   citizen.setLastname("Theophile");
	   citizen.setCategory("Rwanda");
	   
	   nationalId.setId(1);
	   nationalId.setCitizen(citizen);
	   nationalId.setOwnerName(citizen.getFirstname()+" "+citizen.getLastname());
	   nationalId.setNidNo("1 1992 132212 21 1");
	   
	   aioSCard.setNationalId(nationalId);
	   int year = Year.now().getValue();
	   System.out.println("AIOS NID : "+sector.getSectorName()+" From "+sector.getDistrict().getProvince().getProvinceName());
	   
	
	}

}
