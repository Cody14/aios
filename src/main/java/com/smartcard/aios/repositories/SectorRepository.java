package com.smartcard.aios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartcard.aios.models.Sector;



@Repository
public interface SectorRepository extends JpaRepository<Sector, Integer> {

}
