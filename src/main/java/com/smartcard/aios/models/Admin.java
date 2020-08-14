package com.smartcard.aios.models;

import javax.persistence.Entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Admin extends Person {

}
