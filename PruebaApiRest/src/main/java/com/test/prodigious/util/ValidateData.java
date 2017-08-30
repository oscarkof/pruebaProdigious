package com.test.prodigious.util;

import com.test.prodigious.rest.model.Festivity;

/**
 * Class in charge of validate Festivity Object to insert or update
 * @author Oscar Martinez
 */
public class ValidateData {
    
    /**
     * Metodo in charge of validate the festivity necesary information
     * @param festivity Festivity Object
     * @return true in case of the object is valid, false otherwise
     */
    public static boolean validateFestivity(Festivity festivity){
        boolean returnValue = true;
        try {
            if(festivity.getName() == null || festivity.getName().equals("")){
                returnValue = false;
            }else if(festivity.getPlace() == null || festivity.getPlace().equals("")){
                returnValue = false;
            }else if(festivity.getStart() == null){
                returnValue = false;
            }else if(festivity.getEnd() == null){
                returnValue = false;
            }
        } catch (Exception e) {
            returnValue = false;
            e.printStackTrace();
        }
        return returnValue;
    }
    
    /**
     * Metodo in charge of validate the festivity dates
     * @param festivity Festivity Object
     * @return true in case of the object is valid, false otherwise
     */
    public static boolean validateFestivityDates(Festivity festivity){
        boolean returnValue = true;
        try {
            if(festivity.getEnd().compareTo(festivity.getStart()) <= 0){
                returnValue = false;
            }
        } catch (Exception e) {
            returnValue = false;
            e.printStackTrace();
        }
        return returnValue;
    }
}
