/**
 * 
 * $Id: AssociatedExpressionForm.java,v 1.6 2007-10-31 17:21:21 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

public class AssociatedExpressionForm extends BaseForm implements Serializable, AssociatedExpressionData {
    
    private static final long serialVersionUID = 3257405453799404851L;
    
	/**
	 * Default empty constructor
	 * @author pandyas
	 *
	 */
	public AssociatedExpressionForm() {}
	
	protected String name;	
	protected String expressionLevel;
	protected String engineeredGeneID;
	
	protected String organ;
	protected String organTissueName;
	protected String organTissueCode;
	protected String comments;	
	
	public String getEngineeredGeneID() {
		return engineeredGeneID;
	}
	public void setEngineeredGeneID(String engineeredGeneID) {
		this.engineeredGeneID = engineeredGeneID;
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
	 * @return Returns the expressionLevel.
	 */
	public String getExpressionLevel() {
		return expressionLevel;
	}
	/**
	 * @param expressionLevel The expressionLevel to set.
	 */
	public void setExpressionLevel(String expressionLevel) {
		this.expressionLevel = expressionLevel;
	}
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
	}
	public String getOrganTissueCode() {
		return organTissueCode;
	}
	public void setOrganTissueCode(String organTissueCode) {
		this.organTissueCode = organTissueCode;
	}
	public String getOrganTissueName() {
		return organTissueName;
	}
	public void setOrganTissueName(String organTissueName) {
		this.organTissueName = organTissueName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}	
}
