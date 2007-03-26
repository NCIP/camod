/*
 * $Id: InducedMutationForm.java,v 1.8 2007-03-26 12:03:10 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

public class InducedMutationForm extends BaseForm implements Serializable, InducedMutationData {
    
    private static final long serialVersionUID = 3257175453799404851L;
    
	/**
	 * Default empty constructor
	 * @author rajputs
	 */
	public InducedMutationForm() {}
	
	protected String type;
	protected String otherType;
	protected String casNumber;
	protected String geneId;
	protected String name;
	protected String description;
	protected String observation;
	protected String methodOfObservation;
    protected String comments;
	protected String mgiNumber;
	protected String zfinNumber;
	protected String rgdNumber;	
	
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */	
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Returns the otherType.
	 */
	public String getOtherType() {
		return otherType;
	}
	/**
	 * @param otherType The otherType to set.
	 */	
	public void setOtherType(String otherType) {
		this.otherType = otherType;
	}	
	/**
	 * @return Returns the casNumber.
	 */
	public String getCasNumber() {
		return casNumber;
	}
	/**
	 * @param casNumber The casNumber to set.
	 */	
	public void setCasNumber(String casNumber) {
		this.casNumber = casNumber;
	}	
	/**
	 * @return Returns the geneId.
	 */
	public String getGeneId() {
		return geneId;
	}
	/**
	 * @param geneId The geneId to set.
	 */
	public void setGeneId(String geneId) {
		this.geneId = geneId;
	}	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}	
	/**
	 * @return Returns the observation.
	 */
	public String getObservation() {
		return observation;
	}
	/**
	 * @param observation The observation to set.
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}
	/**
	 * @return Returns the methodOfObservation.
	 */
	public String getMethodOfObservation() {
		return methodOfObservation;
	}
	/**
	 * @param methodOfObservation The methodOfObservation to set.
	 */
	public void setMethodOfObservation(String methodOfObservation) {
		this.methodOfObservation = methodOfObservation;
	}	
    
    /**
     * @return Returns the comments.
     */
    public String getComments() {
        return comments;
    }
    /**
     * @param comments The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    
	/**
	 * @return Returns the mgiNumber.
	 */
	public String getMgiNumber() {
		return mgiNumber;
	}
	/**
	 * @param mgiNumber The mgiNumber to set.
	 */
	public void setMgiNumber(String mgiNumber) {
		this.mgiNumber = mgiNumber;
	}
	public String getRgdNumber() {
		return rgdNumber;
	}
	public void setRgdNumber(String rgdNumber) {
		this.rgdNumber = rgdNumber;
	}
	public String getZfinNumber() {
		return zfinNumber;
	}
	public void setZfinNumber(String zfinNumber) {
		this.zfinNumber = zfinNumber;
	}	
}
