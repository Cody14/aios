package com.smartcard.aios.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class AioSCard extends Card {

	private String qrcodePhoto;
	
	private String citizenUsername;
	
	private String aioscardNo;
	
	private String littleCardNo;
	
	private String cvv;
	
	private String ownerStatus;
	
	private String nid="No";
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate issuedDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expireDate;
	
	@OneToOne
	@JoinColumn(name = "nationalId", referencedColumnName = "id")
    private NationalId nationalId;
}
