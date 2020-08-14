package com.smartcard.aios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartcard.aios.models.DrivingLicense;




@Repository
public interface DrivingLicenseRepository extends JpaRepository<DrivingLicense, Integer> {

	public DrivingLicense findByCitizenUsername(String citizenUsername);
	
	
	 @Query(value = " select * from driving_license d where d.citizen_username like %:keyword%  ", nativeQuery = true)
	 List<DrivingLicense> findByKeyowrd(@Param("keyword") String keyword); //SEARCH BY DRAVING LICENSE'S OWNER USERNAME
	 
	 @Query(value = " select * from driving_license d where d.citizen_username like %:keyword%  ", nativeQuery = true)
	 DrivingLicense findByUsername(@Param("keyword") String keyword); //SEARCH BY DRAVING LICENSE'S OWNER USERNAME
	 
}
