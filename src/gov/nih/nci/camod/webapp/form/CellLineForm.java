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
public class CellLineForm extends BaseForm implements Serializable {
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public CellLineForm() {}
	
	// Id is of type String since it comes from the presentation layer
	protected String id;
	protected String components;
	protected String experiment;
	protected String name;
	protected String results;
	/**
	 * @return Returns the components.
	 */
	public String getComponents() {
		return components;
	}
	/**
	 * @param components The components to set.
	 */
	public void setComponents(String components) {
		this.components = components;
	}
	/**
	 * @return Returns the experiment.
	 */
	public String getExperiment() {
		return experiment;
	}
	/**
	 * @param experiment The experiment to set.
	 */
	public void setExperiment(String experiment) {
		this.experiment = experiment;
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
	 * @return Returns the results.
	 */
	public String getResults() {
		return results;
	}
	/**
	 * @param results The results to set.
	 */
	public void setResults(String results) {
		this.results = results;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false
    }
}
