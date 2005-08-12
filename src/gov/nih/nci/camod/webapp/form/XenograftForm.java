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
public class XenograftForm extends BaseForm implements Serializable {
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public XenograftForm() {}
	
	// id is of type String since it comes from the presentation layer. This
	// form does not include properties from the parent class of Xenograft,
	// AbstractCancerModel
	protected String id;
	protected String administrativeSite;
	protected String geneticManipulation;
	protected String type;
	protected String modificationDescription;
	protected String parentalCellLineName;
	protected String name;
	
	/**
	 * @return Returns the administrativeSite.
	 */
	public String getAdministrativeSite() {
		return administrativeSite;
	}
	/**
	 * @param administrativeSite The administrativeSite to set.
	 */
	public void setAdministrativeSite(String administrativeSite) {
		this.administrativeSite = administrativeSite;
	}
	/**
	 * @return Returns the geneticManipulation.
	 */
	public String getGeneticManipulation() {
		return geneticManipulation;
	}
	/**
	 * @param geneticManipulation The geneticManipulation to set.
	 */
	public void setGeneticManipulation(String geneticManipulation) {
		this.geneticManipulation = geneticManipulation;
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
	/**
	 * @return Returns the modificationDescription.
	 */
	public String getModificationDescription() {
		return modificationDescription;
	}
	/**
	 * @param modificationDescription The modificationDescription to set.
	 */
	public void setModificationDescription(String modificationDescription) {
		this.modificationDescription = modificationDescription;
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
	 * @return Returns the parentalCellLineName.
	 */
	public String getParentalCellLineName() {
		return parentalCellLineName;
	}
	/**
	 * @param parentalCellLineName The parentalCellLineName to set.
	 */
	public void setParentalCellLineName(String parentalCellLineName) {
		this.parentalCellLineName = parentalCellLineName;
	}
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
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false
    }
}
