package com.test.prodigious.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class responsible for encapsulating the information of a custom response, 
 * such as an error with some status code defined by the initial requirements of the technical test
 * @author Oscar Martinez
 */
@XmlRootElement(name="responseCustom")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseCustom {
    private int statusCode;
    private String statusMessage;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
    
}
