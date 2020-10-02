package com.smartcard.aios.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.smartcard.aios.models.PieStat;


@Repository
public interface PieStatRepository extends JpaRepository<PieStat, Integer> {
   
	
   
   
   
}
