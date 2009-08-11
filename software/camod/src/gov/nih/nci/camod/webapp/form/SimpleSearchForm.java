/*
 * $Id: SimpleSearchForm.java,v 1.3 2006-04-17 19:09:19 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

public class SimpleSearchForm extends BaseForm implements Serializable {
    
    private static final long serialVersionUID = 3257045453799404851L;
    
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
