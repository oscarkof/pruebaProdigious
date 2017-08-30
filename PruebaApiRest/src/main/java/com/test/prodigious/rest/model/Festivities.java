/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.prodigious.rest.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author osmar
 */
@XmlRootElement(name="festivities")
@XmlAccessorType(XmlAccessType.FIELD)
public class Festivities {

    @XmlElement(name = "festivity")
    private List<Festivity> festivities = null;

    public List<Festivity> getFestivities() {
        return festivities;
    }

    public void setFestivities(List<Festivity> festivities) {
        this.festivities = festivities;
    }
    
    
}
