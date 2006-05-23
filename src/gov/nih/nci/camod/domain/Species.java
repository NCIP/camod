/*
 * $Id: Species.java,v 1.6 2006-05-23 18:15:58 georgeda Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2006/05/10 14:13:51  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.4  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.HashCodeUtil;
import java.io.Serializable;
import java.util.*;


public class Species extends BaseObject implements Serializable
{
    private static final long serialVersionUID = 3258615453799404851L;
    private String scientificName;
    private String scientificNameUnctrlVocab;
    private String commonName;
    private String commonNameUnctrlVocab;
    private String abbreviation;
    private String conceptCode;
    private Set<Strain> strainCollection = new HashSet<Strain>();


    /**
     * method to return the display name used in dropdown lists
     * @return Returns the display name
     */
    public String getDisplayName()
    {
        String theDisplayName = "";
        if (scientificName != null || commonName != null)
        {
            if (scientificName != null && commonName != null)
            {
                theDisplayName = commonName.trim() + " (" + scientificName.trim() + ")";
            }
            else if (scientificName != null)
            {
                theDisplayName = scientificName.trim();
            }
            else
            {
                theDisplayName = commonName.trim();
            }
        }
        else if (scientificNameUnctrlVocab != null || commonNameUnctrlVocab != null)
        {
            if (scientificNameUnctrlVocab != null)
            {
                theDisplayName = scientificNameUnctrlVocab.trim();
            }
            else
            {
                theDisplayName = commonNameUnctrlVocab.trim();
            }
        }
        return theDisplayName;
    }

    /**
     * @return Returns the scientificName.
     */
    public String getScientificName()
    {
        return scientificName;
    }

    /**
     * @param scientificName
     *            The scientificName to set.
     */
    public void setScientificName(String scientificName)
    {
        this.scientificName = scientificName;
    }

    /**
     * @return Returns the scientificNameUnctrlVocab.
     */
    public String getScientificNameUnctrlVocab()
    {
        return scientificNameUnctrlVocab;
    }

    /**
     * @param scientificNameUnctrlVocab
     *            The scientificNameUnctrlVocab to set.
     */
    public void setScientificNameUnctrlVocab(String scientificNameUnctrlVocab)
    {
        this.scientificNameUnctrlVocab = scientificNameUnctrlVocab;
    }

    /**
     * @return Returns the commonName.
     */
    public String getCommonName()
    {
        return commonName;
    }

    /**
     * @param commonName
     *            The commonName to set.
     */
    public void setCommonName(String commonName)
    {
        this.commonName = commonName;
    }

    /**
     * @return Returns the commonNameUnctrlVocab.
     */
    public String getCommonNameUnctrlVocab()
    {
        return commonNameUnctrlVocab;
    }

    /**
     * @param commonNameUnctrlVocab
     *            The commonNameUnctrlVocab to set.
     */
    public void setCommonNameUnctrlVocab(String commonNameUnctrlVocab)
    {
        this.commonNameUnctrlVocab = commonNameUnctrlVocab;
    }

    /**
     * @return Returns the abbreviation.
     */
    public String getAbbreviation()
    {
        return abbreviation;
    }

    /**
     * @param abbreviation
     *            The abbreviation to set.
     */
    public void setAbbreviation(String abbreviation)
    {
        this.abbreviation = abbreviation;
    }

    /**
     * @return Returns the conceptCode.
     */
    public String getConceptCode()
    {
        return conceptCode;
    }

    /**
     * @return Returns the EVS Preferred displayName

     public String getEVSPreferredDescription()
     {
     return EvsTreeUtil.getEVSPreferedDescription(conceptCode);
     }
     */
    /**
     * @param conceptCode
     *            The conceptCode to set.
     */
    public void setConceptCode(String conceptCode)
    {
        this.conceptCode = conceptCode;
    }

    /**
     * @return Returns the strainCollection.
     */
    public Set<Strain> getStrainCollection()
    {
        return strainCollection;
    }

    /**
     * @param strainCollection
     *            The strainCollection to set.
     */
    public void setStrainCollection(Set<Strain> strainCollection)
    {
        this.strainCollection = strainCollection;
    }

    public void addStrain(Strain strain)
    {
        strainCollection.add(strain);
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getScientificName() + " - " + this.getCommonName();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final Species obj = (Species) o;
        if (HashCodeUtil.notEqual(this.getScientificName(), obj.getScientificName()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getScientificName());
        return result + super.hashCode();
    }
    /*    
     public int compareTo(Object o)
     {
     // compare by evs description name if possible, otherwise organ name
     if ((o instanceof Species) && (this.getEVSPreferredDescription() != null) && (((Species) o).getEVSPreferredDescription() != null))
     {
     int result = this.getEVSPreferredDescription().compareTo(((Species) o).getEVSPreferredDescription());
     if (result != 0)
     {
     return result;
     }
     }
     else if ((o instanceof Species) && (this.getScientificName() != null) && (((Species) o).getScientificName() != null))
     {
     int result = this.getScientificName().compareTo(((Species) o).getScientificName());
     if (result != 0)
     {
     return result;
     }
     }

     return super.compareTo(o);
     }
     */
}
