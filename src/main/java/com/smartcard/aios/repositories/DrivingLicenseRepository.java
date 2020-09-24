package com.smartcard.aios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartcard.aios.models.DrivingLicense;
import com.smartcard.aios.models.NationalId;




@Repository
public interface DrivingLicenseRepository extends JpaRepository<DrivingLicense, Integer> {

	public DrivingLicense findByCitizenUsername(String citizenUsername);
	
	
	 @Query(value = " select * from driving_license d where d.citizen_username like %:keyword%  ", nativeQuery = true)
	 List<DrivingLicense> findByKeyowrd(@Param("keyword") String keyword); //SEARCH BY DRAVING LICENSE'S OWNER USERNAME
	 
	 @Query(value = " select * from driving_license d where d.citizen_username like %:keyword%  ", nativeQuery = true)
	 DrivingLicense findByUsername(@Param("keyword") String keyword); //SEARCH BY DRAVING LICENSE'S OWNER USERNAME
	 
	 @Query(value = " select * from driving_license d where d.dl_status='active' ", nativeQuery = true)
	 List<DrivingLicense> activeDlList(); // list of active dls
	 
	 @Query(value = " select * from driving_license d where d.dl_status='inactive' ", nativeQuery = true)
	 List<DrivingLicense> inactiveDlList(); // list of active dls
	 
	 @Query(value = " select * from driving_license d where d.link_status='linked' ", nativeQuery = true)
	 List<DrivingLicense> linkedDlList(); // list of active dls
	 
	 
	 
	 
	 
	 @Transactional 
	   @Modifying
	   @Query(value = "UPDATE driving_license d SET d.dl_status =:status  where d.id =:id", nativeQuery = true)
	   void disable(@Param("id") Integer id,@Param("status") String status); //disable dl
	 
}
