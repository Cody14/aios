package com.smartcard.aios.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class District implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String districtName;
	@ManyToOne
	private Province province;
	@OneToMany(mappedBy = "district")
	private List<Sector> sectors;
	@OneToMany(mappedBy = "birthPlace")
	private List<Citizen> citizens;
	@OneToMany(mappedBy = "dlplaceIssue")
	private List<DrivingLicense> drivingLicenses;

	
	
}
