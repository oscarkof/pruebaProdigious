package com.test.prodigious.rest.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A representative class of an specific Festivity node that exist in the XML file,
 * this class is used in Hibernate to storage or update Database information
 * @author Oscar Martinez
 */
@XmlRootElement(name="festivity")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="festivity")
public class Festivity {

    @NotNull
    @Size(min = 1, max = 50)
    @Id
    @Column(name="name")
    private String name;
    @Column(name="start")
    private Date start;
    @Column(name="end_")
    private Date end;
    @Column(name="place")
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
