/**
 * 
 * $Id: DropdownOption.java,v 1.1 2005-10-13 20:48:11 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.webapp.util;

/**
 * 
 * Simple class to handle struts dropdowns where the value is not the same as
 * the label
 * 
 */
public class DropdownOption {

    String myLabel;
    String myValue;

    DropdownOption(String inLabel, String inValue) {
        myLabel = inLabel;
        myValue = inValue;
    }

    public String getLabel() {
        return myLabel;
    }

    public String getValue() {
        return myValue;
    }
}
