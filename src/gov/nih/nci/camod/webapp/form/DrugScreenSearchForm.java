/*
 * $Id: DrugScreenSearchForm.java,v 1.2 2006-04-17 19:09:19 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.webapp.form;

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
	}
}
