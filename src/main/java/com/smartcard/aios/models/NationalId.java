package com.smartcard.aios.models;



import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class NationalId extends Card {
	 @ManyToOne
	 @JoinColumn(name="placeIssueId", insertable=false, updatable=false)
     private Sector NIDplaceIssue;
	 private Integer placeIssueId;
     private String nidNo;
     private String citizenUsername;
     
     @OneToOne(mappedBy = "nationalId")
     private AioSCard aioSCard;

}
