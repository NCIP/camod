package gov.nih.nci.camod.webapp.form;

/**
 * @author pandyas
 * 
 * $Id: AvailabilityForm.java,v 1.2 2005-10-26 20:14:25 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
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
   

}
