
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.camod.util.HashCodeUtil;

import java.io.Serializable;

public class StainingMethod extends BaseObject implements Comparable,
		Serializable {
	private static final long serialVersionUID = 3258615453799404851L;

	private String name;
    private String nameUnctrlVocab;
	private String conceptCode;

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
     * @return Returns the nameUnctrlVocab.
     */
    public String getNameUnctrlVocab()
    {
        return nameUnctrlVocab;
    }

    /**
     * @param nameUnctrlVocab
     *            The nameUnctrlVocab to set.
     */
    public void setNameUnctrlVocab(String nameUnctrlVocab)
    {
        this.nameUnctrlVocab = nameUnctrlVocab;
    }	
	/**
	 * @return Returns the conceptCode.
	 */
	public String getConceptCode() {
		return conceptCode;
	}

	/**
	 * @return Returns the EVS Preferred displayName
	 */
	public String getEVSPreferredDescription() {
		return EvsTreeUtil.getEVSPreferedDescription(conceptCode);
	}

	/**
	 * @param conceptCode
	 *            The conceptCode to set.
	 */
	public void setConceptCode(String conceptCode) {
		this.conceptCode = conceptCode;
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
		final Organ obj = (Organ) o;
		if (HashCodeUtil.notEqual(this.getName(), obj.getName()))
			return false;
		return true;
	}

	public int hashCode() {
		int result = HashCodeUtil.SEED;
		result = HashCodeUtil.hash(result, this.getName());
		return result + super.hashCode();
	}

	public int compareTo(Object o) {
		// compare by evs description name if possible, otherwise organ name
		if ((o instanceof Organ) && (this.getEVSPreferredDescription() != null)
				&& (((Organ) o).getEVSPreferredDescription() != null)) {
			int result = this.getEVSPreferredDescription().compareTo(
					((Organ) o).getEVSPreferredDescription());
			if (result != 0) {
				return result;
			}
		} else if ((o instanceof Organ) && (this.getName() != null)
				&& (((Organ) o).getName() != null)) {
			int result = this.getName().compareTo(((Organ) o).getName());
			if (result != 0) {
				return result;
			}
		}

		return super.compareTo(o);
	}
}
