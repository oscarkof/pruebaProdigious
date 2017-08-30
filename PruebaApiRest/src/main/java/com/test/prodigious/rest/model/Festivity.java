/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.prodigious.rest.model;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author osmar
 */
@XmlRootElement(name="festivity")
@XmlAccessorType(XmlAccessType.FIELD)
public class Festivity {

    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    private Date start;
    private Date end;
    private String place;

    public String getName() {
        return name;
    }

   
    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    
    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    
    public void setEnd(Date end) {
        this.end = end;
    }

    public String getPlace() {
        return place;
    }

    
    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Festivity{" + "name=" + name + ", start=" + start + ", end=" + end + ", place=" + place + '}';
    }
}
