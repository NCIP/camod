/**
 * 
 * $Id: DropdownOption.java,v 1.2 2005-10-17 14:11:23 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/10/13 20:48:11  georgeda
 * Correctly handle the PI
 *
 * 
 */
package gov.nih.nci.camod.webapp.util;

/**
 * 
 * Simple class to handle struts dropdowns where the value is not the same as
 * the label
 * 
 */
public class DropdownOption implements Comparable {

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

    public int compareTo(Object inObject) {
        
        DropdownOption theOption = (DropdownOption) inObject;
        
        return this.myLabel.compareTo(theOption.myLabel);
    }
}
