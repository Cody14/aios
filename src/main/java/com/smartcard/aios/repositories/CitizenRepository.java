package com.smartcard.aios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import com.smartcard.aios.models.Citizen;


@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {
   
	Citizen findAllByUsername(String username);
	
    Citizen findByUsername(String username);
   
   
   
   @Query(value = " select * from citizen c where c.username like %:keyword%  ", nativeQuery = true)
   List<Citizen> findByKeyowrd(@Param("keyword") String keyword); //SEARCH BY CITIZEN USERNAME
   
   
   @Query(value = " select * from citizen c where c.form_request like %:status%  ", nativeQuery = true)
   List<Citizen> pendingList(@Param("status") String status); //SEARCH BY CITIZEN USERNAME
   
   
   
   @Transactional 
   @Modifying
   @Query(value = "UPDATE citizen c SET c.form_request =:status  where c.id =:cid", nativeQuery = true)
   void reject(@Param("cid") Integer cid,@Param("status") String status); //SEARCH BY CITIZEN USERNAME
   
   
   
   
   @Transactional 
   @Modifying
   @Query(value = "UPDATE citizen c SET c.form_request =:status, c.pass_key =:passKey  where c.id =:cid", nativeQuery = true)
   void accept(@Param("cid") Integer cid,@Param("status") String status,@Param("passKey") String passKey); //SEARCH BY CITIZEN USERNAME
   
   
   
}
