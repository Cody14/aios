package com.smartcard.aios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartcard.aios.models.District;



@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

}
