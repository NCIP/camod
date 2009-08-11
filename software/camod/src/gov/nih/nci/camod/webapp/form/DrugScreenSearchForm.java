/*
 * $Id: DrugScreenSearchForm.java,v 1.3 2008-05-27 14:35:32 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.webapp.form;

import gov.nih.nci.camod.util.SafeHTMLUtil;

import java.io.Serializable;

public class DrugScreenSearchForm extends BaseForm implements Serializable {
    
    private static final long serialVersionUID = 3257045453799404851L;
    
	/**
	 * Default empty constructor
	 * @author nschroedl
	 *
	 */
	public DrugScreenSearchForm() {}
	
	protected String NSCNumber;
	protected boolean doYeast = false;
	protected boolean doInvivo = false;
	protected boolean doPreClinical = false;
	protected boolean doClinical = false;

	public boolean isDoClinical() {
		return doClinical;
	}
	public void setDoClinical(boolean doClinical) {
		this.doClinical = doClinical;
	}
	public boolean isDoInvivo() {
		return doInvivo;
	}
	public void setDoInvivo(boolean doInvivo) {
		this.doInvivo = doInvivo;
	}
	public boolean isDoPreClinical() {
		return doPreClinical;
	}
	public void setDoPreClinical(boolean doPreClinical) {
		this.doPreClinical = doPreClinical;
	}
	public boolean isDoYeast() {
		return doYeast;
	}
	public void setDoYeast(boolean doYeast) {	
		this.doYeast = doYeast;
	}
	public String getNSCNumber() {
		return NSCNumber;
	}
	public void setNSCNumber(String number) {
		NSCNumber = number;
        // Clean the parameter
        if (this.NSCNumber != null && !this.NSCNumber.equals(""))  {
                this.NSCNumber = SafeHTMLUtil.cleanModelDescriptor(this.NSCNumber);
        } 		
	}
}
