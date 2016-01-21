/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.model;

import com.demo.adapter.JaxbBigDecimalAdapter;
import com.demo.adapter.JaxbCategoryItemAdapter;
import com.demo.adapter.JaxbDateAdapter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author sanayapc
 */
@XmlRootElement(name = "customer")
public class Customer {
   private String name;
   private String address;
   private String gender;
   private Date date;
   private String curs;
   private BigDecimal bal;
   
   @XmlJavaTypeAdapter(JaxbCategoryItemAdapter.class)
   @XmlElement(name = "name")
   
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    @XmlElement(name = "address")
    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    @XmlElement(name = "Gender")
    /**
     * @return the gender
     */
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
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    @XmlElement    
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the curs
     */
    @XmlElement
    public String getCurs() {
        return curs;
    }

    /**
     * @param curs the curs to set
     */
    public void setCurs(String curs) {
        this.curs = curs;
    }

    /**
     * @return the bal
     */
    @XmlJavaTypeAdapter(JaxbBigDecimalAdapter.class)
    @XmlElement(name = "Balance")
    public BigDecimal getBal() {
        return bal;
    }

    /**
     * @param bal the bal to set
     */
    public void setBal(BigDecimal bal) {
        this.bal = bal;
    }
    
    
	// for csv demo only
	public String getCsvDob() {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(getDate());

	}
}
