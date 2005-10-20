/**
 *
 * @author pandyas
 * 
 * $Id: ClinicalMarkerForm.java,v 1.3 2005-10-20 20:32:45 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

public class ClinicalMarkerForm extends BaseForm implements Serializable {

    private static final long serialVersionUID = 3257325453799404851L;

    public ClinicalMarkerForm() {
    }

    protected String name;
    protected String value;

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
