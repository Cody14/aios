package com.smartcard.aios.models;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)

public class Citizen extends Person {

	private String category;
	@ManyToOne
	@JoinColumn(name="birthPlaceId", insertable=false, updatable=false)
	private District birthPlace;
	private Integer birthPlaceId;
	private String martialStatus;
	private String motherName;
	private String fatherName;
	@ManyToOne
	@JoinColumn(name="currentPlaceId", insertable=false, updatable=false)
	private Village currentPlace;
	private Integer currentPlaceId;
	@ManyToOne
	private User user;
	private String username;

	private String photo;
	private String passKey;
	private String formRequest = "Pending";
}
