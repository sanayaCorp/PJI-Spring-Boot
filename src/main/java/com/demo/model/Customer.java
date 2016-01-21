/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.model;

import com.demo.adapter.JaxbBigDecimalAdapter;
import com.demo.adapter.JaxbDateAdapter;
import java.io.Serializable;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author sanayapc
 */
@Entity
@Table(name = "TICKETS")
@Cacheable(value = false)
@XmlRootElement(name = "customer")
public class Customer implements Serializable {
   @Id
   private String name;
   private String address;
   private String gender;
   private String dob;
   private String currency;
   private BigDecimal balance;

    /**
     * @return the name
     */
   
   @XmlElement(name = "name")
   public String getName() {
        return name;
   }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
        
    /**
     * @return the address
     */
    @XmlElement(name = "address")
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
        
    /**
     * @return the gender
     */
    @XmlElement(name = "Gender")
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
     * @return the date
     */    
    @XmlElement(name = "DOB")   
    public String getDob() {
        return dob;
    }

    /**
     * @param date the date to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the curs
     */
    @XmlElement(name = "Currency")
    public String getCurrency() {
        return currency;
    }

    /**
     * @param curs the curs to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the bal
     */
    @XmlJavaTypeAdapter(JaxbBigDecimalAdapter.class)
    @XmlElement(name = "Balance")
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param bal the bal to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    
    // for csv demo only
    public String getCsvDob() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");        
        return dateFormat.format(getDob());
    }
    
}
