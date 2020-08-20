package com.smartcard.aios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartcard.aios.models.AioSCard;
import com.smartcard.aios.models.Citizen;





@Repository
public interface AioSCardRepository extends JpaRepository<AioSCard, Integer> {
    
	 public  AioSCard  findByCitizenUsername(String citizenUsername);
	 
	
	
	   @Query(value = " select * from aioscard n where n.citizen_username like %:keyword%  ", nativeQuery = true)
	   List<AioSCard> findByKeyword(@Param("keyword") String keyword); //SEARCH BY AIOS CARD ID'S OWNER USERNAME
	   
	   @Query(value = " select * from aioscard n where n.citizen_username like %:keyword%  ", nativeQuery = true)
	   AioSCard findByKeywordUsername(@Param("keyword") String keyword); //SEARCH BY AIOS CARD ID'S OWNER USERNAME
	   

}
