package com.smartcard.aios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartcard.aios.models.Village;




@Repository
public interface VillageRepository extends JpaRepository<Village, Integer> {

}
