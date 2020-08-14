package com.smartcard.aios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartcard.aios.models.Cell;




@Repository
public interface CellRepository extends JpaRepository<Cell, Integer> {

}
