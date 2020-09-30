package com.smartcard.aios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartcard.aios.models.Citizen;
import com.smartcard.aios.models.NationalId;




@Repository
public interface NationalIdRepository extends JpaRepository<NationalId, Integer> {
    
	 public  NationalId  findByCitizenUsername(String citizenUsername);
	
	   @Query(value = " select * from national_id n where n.citizen_username like %:keyword%  ", nativeQuery = true)
	   List<NationalId> findByKeyowrd(@Param("keyword") String keyword); //SEARCH BY NATIONAL ID'S OWNER USERNAME
	   

	   
	   @Transactional 
	   @Modifying
	   @Query(value = "UPDATE national_id n SET n.nid_status =:status  where n.id =:id", nativeQuery = true)
	   void disable(@Param("id") Integer id,@Param("status") String status); //disable nid
	   
	   
	   @Query(value = " select * from national_id n where n.nid_status='active' ", nativeQuery = true)
	   List<NationalId> activeNidList(); // list of active nids
	   
	   
	  
	   
	   
	  
	   
	   
	   
	   @Query(value = " select * from national_id n where n.nid_status='inactive' ", nativeQuery = true)
	   List<NationalId> inactiveNidList(); // list of inactive nids
	   
	   @Query(value = " select * from national_id n where n.link_status='linked' ", nativeQuery = true)
	   List<NationalId> linkedNidList(); // list of active nids
	   
	   
	   
}
