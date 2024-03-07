package org.acme.dalpra.ui.view.persone.entity;

import java.io.Serializable;


public class Persone implements Serializable{
    private Long id;
    private String firstName;
    private String lastName;
    private String taxIdCode;


    public Persone( String firstName, String lastName, String taxIdCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.taxIdCode = taxIdCode;
    }

    public Persone() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTaxIdCode() {
        return taxIdCode;
    }

    public void setTaxIdCode(String taxIdCode) {
        this.taxIdCode = taxIdCode;
    }
    
    
    
    
}
