/**
 * @author pandyas
 * 
 * $Id: AvailabilityData.java,v 1.1 2005-10-21 16:06:33 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
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
    
    public String getPrincipalInvestigator();

    public void setPrincipalInvestigator(String principalInvestigator);

}
