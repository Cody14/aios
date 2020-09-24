package com.smartcard.aios.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartcard.aios.models.AioSCard;
import com.smartcard.aios.models.Citizen;





@Repository
public interface AioSCardRepository extends JpaRepository<AioSCard, Integer> {
    
	 public  AioSCard  findByCitizenUsername(String citizenUsername);
	 
	
	
	   @Query(value = " select * from aioscard n where n.citizen_username like %:keyword%  ", nativeQuery = true)
	   List<AioSCard> findByKeyword(@Param("keyword") String keyword); //SEARCH BY AIOS CARD ID'S OWNER USERNAME
	   
	   @Query(value = " select * from aioscard n where n.citizen_username like %:keyword%  ", nativeQuery = true)
	   AioSCard findByKeywordUsername(@Param("keyword") String keyword); //SEARCH BY AIOS CARD ID'S OWNER USERNAME
	   
	   
	   @Transactional 
	   @Modifying
	   @Query(value = "UPDATE aioscard a SET a.national_id =null  where a.id =:id", nativeQuery = true)
	   void unlinkNid(@Param("id") Integer id); //unlink nid
	   
	   @Transactional 
	   @Modifying
	   @Query(value = "UPDATE aioscard a SET a.driving_license =null  where a.id =:id", nativeQuery = true)
	   void unlinkDL(@Param("id") Integer id); //unlink dl
	   
	   @Transactional 
	   @Modifying
	   @Query(value = "UPDATE aioscard a SET a.health_insurance =null  where a.id =:id", nativeQuery = true)
	   void unlinkHL(@Param("id") Integer id); //unlink dl
	   

}
