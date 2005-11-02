/*
 * Created on Aug 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;


/**
 * @author pandyas
 * 
 */
public class ClinicalMarkerForm extends BaseForm implements Serializable, ClinicalMarkerData {

    private static final long serialVersionUID = 3257325453799404851L;

    /**
     * Default empty constructor
     * 
     * @author pandyas
     * 
     * TODO To change the template for this generated type comment go to Window -
     * Preferences - Java - Code Style - Code Templates
     */
    public ClinicalMarkerForm() {
    }
    
	protected String parentHistopathID;    

    protected String name;
    protected String value;

    
	/**
	 * @return Returns the parentHistopathID.
	 */	
	public String getParentHistopathID() {
		return parentHistopathID;
	}
	/**
	 * @param parentHistopathID The parentHistopathID to set.
	 */	
	public void setParentHistopathID( String parentHistopathID ) {
		this.parentHistopathID = parentHistopathID;
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
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }
}
