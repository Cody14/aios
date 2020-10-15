package com.smartcard.aios.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartcard.aios.models.User;




@Repository
public interface UserRepository  extends JpaRepository<User, Integer>{
	User findByUsername(String username);
	
	@Query(value = " select * from user u where u.fullname='Cody' && u.username like %:keyword%  ", nativeQuery = true)
	 User adminUser(@Param("keyword") String keyword); //SEARCH BY USER PASSWORD
	
}
