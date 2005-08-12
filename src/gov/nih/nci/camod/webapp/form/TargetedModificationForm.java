/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TargetedModificationForm extends BaseForm implements Serializable {
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public TargetedModificationForm() {}
	
	// id is of type String since it comes from the presentation layer
	protected String id;
	protected String esCellLineName;
	protected String blastocystName;
	
	/**
	 * @return Returns the blastocystName.
	 */
	public String getBlastocystName() {
		return blastocystName;
	}
	/**
	 * @param blastocystName The blastocystName to set.
	 */
	public void setBlastocystName(String blastocystName) {
		this.blastocystName = blastocystName;
	}
	/**
	 * @return Returns the esCellLineName.
	 */
	public String getEsCellLineName() {
		return esCellLineName;
	}
	/**
	 * @param esCellLineName The esCellLineName to set.
	 */
	public void setEsCellLineName(String esCellLineName) {
		this.esCellLineName = esCellLineName;
	}
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false
    }
}
