package com.smartcard.aios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartcard.aios.models.HealthInsurance;
import com.smartcard.aios.models.NationalId;





@Repository
public interface HealthInsuranceRepository extends JpaRepository<HealthInsurance, Integer> {

	 public  HealthInsurance  findByCitizenUsername(String citizenUsername);
		
	   @Query(value = " select * from health_insurance h where h.citizen_username like %:keyword%  ", nativeQuery = true)
	   List<HealthInsurance> findByKeyowrd(@Param("keyword") String keyword); //SEARCH BY HealthInsurance'S OWNER USERNAME
	   
	   @Query(value = " select * from health_insurance h where h.hi_status='active' ", nativeQuery = true)
	   List<HealthInsurance> activeHiList(); // list of active his
	   
	   @Query(value = " select * from health_insurance h where h.hi_status='inactive' ", nativeQuery = true)
	   List<HealthInsurance> inactiveHiList(); // list of active his
	   
	   @Transactional 
	   @Modifying
	   @Query(value = "UPDATE health_insurance h SET h.hi_status =:status  where h.id =:id", nativeQuery = true)
	   void disable(@Param("id") Integer id,@Param("status") String status); //disable hl
	   
	   @Query(value = " select * from health_insurance h where h.link_status='linked' ", nativeQuery = true)
	   List<HealthInsurance> linkedHIList(); // list of active hl
	
}
