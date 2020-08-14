package com.smartcard.aios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartcard.aios.models.NationalId;




@Repository
public interface NationalIdRepository extends JpaRepository<NationalId, Integer> {
    
	 public  NationalId  findByCitizenUsername(String citizenUsername);
	
	   @Query(value = " select * from national_id n where n.citizen_username like %:keyword%  ", nativeQuery = true)
	   List<NationalId> findByKeyowrd(@Param("keyword") String keyword); //SEARCH BY NATIONAL ID'S OWNER USERNAME
	   

}
