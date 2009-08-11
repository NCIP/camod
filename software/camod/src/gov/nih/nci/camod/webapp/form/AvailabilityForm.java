/**
 * 
 * $Id: AvailabilityForm.java,v 1.4 2007-10-31 17:51:56 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.form;

/**
 * @author pandyas
 * 
 * $Id: AvailabilityForm.java,v 1.4 2007-10-31 17:51:56 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.2  2005/10/26 20:14:25  pandyas
 * implemented model availability
 *
 * Revision 1.1  2005/10/21 16:06:33  pandyas
 * implementation of animal availability
 *
 *
 * 
 */

import java.io.Serializable;

public class AvailabilityForm extends BaseForm implements Serializable, AvailabilityData {
	
    private static final long serialVersionUID = 3257125453799404851L;	
	
    protected String name;
    protected String source;
    protected String stockNumber;
    protected String principalInvestigator;    
 
    
    public AvailabilityForm() {
    }    
    
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return Returns the stockNumber.
     */
    public String getStockNumber() {
        return stockNumber;
    }

    /**
     * @param stockNumber
     *            The stockNumber to set.
     */
    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the principalInvestigator
	 */
	public String getPrincipalInvestigator() {
		return principalInvestigator;
	}

	/**
	 * @param principalInvestigator the principalInvestigator to set
	 */
	public void setPrincipalInvestigator(String principalInvestigator) {
		this.principalInvestigator = principalInvestigator;
	}
   

}
