/*
 * LogonForm.java
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

public class SimpleSearchForm extends BaseForm implements Serializable {
	/**
	 * Default empty constructor
	 * @author nschroedl
	 *
	 */
	public SimpleSearchForm() {}
	
	protected String keyword;
	protected String piName;
	protected String modelDescriptor;
	protected String organ;
	protected String species;
	
	/**
	 * @return Returns the username.
	 */
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String k) {
		this.keyword = k;
	}
	public void setPiName(String p) {
		this.piName = p;
	}
	public String getPiName() {
		return piName;
	}
	public String getModelDescriptor() {
		return modelDescriptor;
	}
	public void setModelDescriptor(String m) {
		this.modelDescriptor = m;
	}
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String o) {
		this.organ = o;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String s) {
		this.species = s;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {
    }
}
