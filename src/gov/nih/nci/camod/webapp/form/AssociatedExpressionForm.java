package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

/**
 * @author pandyas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssociatedExpressionForm extends BaseForm implements Serializable {
	/**
	 * Default empty constructor
	 * @author pandyas
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public AssociatedExpressionForm() {}
	
	protected String name;	
	protected String expressionLevel;

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
}
