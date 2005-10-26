/**
 * @author pandyas
 * 
 * $Id: AvailabilityData.java,v 1.2 2005-10-26 20:14:25 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/10/21 16:06:33  pandyas
 * implementation of animal availability
 *
 */
package gov.nih.nci.camod.webapp.form;

/**
 * Availability interface
 */
public interface AvailabilityData {
	
    public String getName();
    public void setName(String name);
    public String getStockNumber();
    public void setStockNumber(String stockNumber);
    public String getSource();
    public void setSource(String source);

}
