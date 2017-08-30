package com.test.prodigious.rest.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A representative class of all Festivity nodes that exist in the XML file
 * @author Oscar Martinez
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
