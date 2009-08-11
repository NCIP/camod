/*
 * $Id: MutationIdentifier.java,v 1.9 2007-04-04 13:16:54 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2007/03/19 18:56:11  pandyas
 * Object Model changes for caMOD 2.3 - dee design doc for details
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
public class MutationIdentifier extends BaseObject implements Serializable, Duplicatable
{
    private static final long serialVersionUID = 3259135453799404851L;

    private String mgiId;
    private String zfinId;
    private String rgdId;



    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        if (this.getId() != null)
            result += this.getId();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        return true;
    }

	public String getMgiId() {
		return mgiId;
	}

	public void setMgiId(String mgiId) {
		this.mgiId = mgiId;
	}

	public String getRgdId() {
		return rgdId;
	}

	public void setRgdId(String rgdId) {
		this.rgdId = rgdId;
	}

	public String getZfinId() {
		return zfinId;
	}

	public void setZfinId(String zfinId) {
		this.zfinId = zfinId;
	}
}
