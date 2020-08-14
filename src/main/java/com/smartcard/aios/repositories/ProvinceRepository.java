package com.smartcard.aios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartcard.aios.models.Province;



@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

}
