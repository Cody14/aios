package com.smartcard.aios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartcard.aios.models.HealthInsurance;





@Repository
public interface HealthInsuranceRepository extends JpaRepository<HealthInsurance, Integer> {

	 public  HealthInsurance  findByCitizenUsername(String citizenUsername);
		
	   @Query(value = " select * from health_insurance h where h.citizen_username like %:keyword%  ", nativeQuery = true)
	   List<HealthInsurance> findByKeyowrd(@Param("keyword") String keyword); //SEARCH BY HealthInsurance'S OWNER USERNAME
	
}
