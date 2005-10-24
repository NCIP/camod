/**
 * 
 * $Id: DropdownOption.java,v 1.3 2005-10-24 13:28:39 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2005/10/17 14:11:23  georgeda
 * Sort returned user list
 *
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

	public boolean equals(Object inObject) {

		// check for self-comparison
		if (this == inObject) {
			return true;
		}
		if (!(inObject instanceof DropdownOption)) {
			return false;
		}

		DropdownOption theDropdownOption = (DropdownOption) inObject;

		return theDropdownOption.myValue.equals(this.myValue);
	}
}
