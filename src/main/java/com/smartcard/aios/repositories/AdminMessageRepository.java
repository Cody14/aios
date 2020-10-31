package com.smartcard.aios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartcard.aios.models.AdminMessage;
import com.smartcard.aios.models.Citizen;





@Repository
public interface AdminMessageRepository extends JpaRepository<AdminMessage, Integer> {
	
	 AdminMessage findByCitizenUsername(String username);
	
	
	 @Query(value = " select * from admin_message am where am.message_status  ='read' ", nativeQuery = true)
	 List<AdminMessage> readAdminMessages(); // list of accepted admins Message
	 
	

}
