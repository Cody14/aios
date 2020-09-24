package com.smartcard.aios.models;



import javax.persistence.Entity;
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
public class HealthInsurance extends Card {
       private String rssbNo;
       private String affiliateName;
       private String citizenUsername;
       @OneToOne(mappedBy = "healthInsurance")
       private AioSCard aioSCard;
       private String hi_status= "active";
       private String linkStatus = "linked";
}
