/*
 * $Id: Genotype.java,v 1.2 2007-02-23 21:30:31 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/10/17 16:14:36  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.7  2006/04/19 17:37:37  pandyas
 * Removed TODO text
 *
 * Revision 1.6  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;

/**
 * @author rajputs
 */
public class Genotype extends BaseObject implements Serializable, Duplicatable {
	private static final long serialVersionUID = 3259295453799404851L;

	private String name;

    private AbstractCancerModel cancerModel;	

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
     * @return Returns the cancerModel.
     */
    public AbstractCancerModel getCancerModel()
    {
        return cancerModel;
    }

    /**
     * @param cancerModel
     *            The cancerModel to set.
     */
    public void setCancerModel(AbstractCancerModel cancerModel)
    {
        this.cancerModel = cancerModel;
    }  	

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result = super.toString() + " - ";
		result += this.getName();
		return result;
	}

	public boolean equals(Object o) {
		if (!super.equals(o))
			return false;
		if (!(this.getClass().isInstance(o)))
			return false;
		return true;
	}

}
