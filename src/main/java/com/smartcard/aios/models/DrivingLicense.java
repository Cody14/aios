package com.smartcard.aios.models;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


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
public class DrivingLicense extends Card {
	
    private String citizenUsername;
    private String dlNo;
    @ManyToOne
    @JoinColumn(name="dlplaceIssueId", insertable=false, updatable=false)
    private District dlplaceIssue;
    private int dlplaceIssueId;
    
    private String cat_A="";
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate_A;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate_A;

    private String cat_B="";
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate_B;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate_B;
    
    
    private String cat_C="";
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate_C;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate_C;
    

    
    private String cat_D="";
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate_D;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate_D;
    
    
    private String cat_E="";
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate_E;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate_E;
    
    
    private String cat_A1="";
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate_A1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate_A1;
   
    
    private String cat_B1="";
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate_B1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate_B1;
    
    
    private String cat_D1="";
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate_D1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate_D1;

  
}
