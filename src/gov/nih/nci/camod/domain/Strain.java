/*
 * $Id: Strain.java,v 1.5 2006-04-19 17:37:37 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.HashCodeUtil;

import java.io.Serializable;


public class Strain extends BaseObject implements Serializable
{

    private static final long serialVersionUID = 3258615453799404851L;

    private String name;
    private String nameUnctrlVocab;
    private String abbreviation;
    private String conceptCode;
    private MutationIdentifier mutationIdentifier;
    private Species species;


    /**
     * @return Returns the species.
     */
    public Species getSpecies()
    {
        return species;
    }

    /**
     * @param species
     *            The species to set.
     */
    public void setSpecies(Species species)
    {
        this.species = species;
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name)
    {
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
     * @return Returns the mutationIdentifier.
     */
    public MutationIdentifier getMutationIdentifier()
    {
        return mutationIdentifier;
    }

    /**
     * @param mutationIdentifier
     *            The mutationIdentifier to set.
     */
    public void setMutationIdentifier(MutationIdentifier mutationIdentifier)
    {
        this.mutationIdentifier = mutationIdentifier;
    }

    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getName();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final Strain obj = (Strain) o;
        if (HashCodeUtil.notEqual(this.getName(), obj.getName()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getName());
        return result + super.hashCode();
    }
/*    
    public int compareTo(Object o)
    {
        // compare by evs description name if possible, otherwise organ name
        if ((o instanceof Strain) && (this.getEVSPreferredDescription() != null) && (((Strain) o).getEVSPreferredDescription() != null))
        {
            int result = this.getEVSPreferredDescription().compareTo(((Organ) o).getEVSPreferredDescription());
            if (result != 0)
            {
                return result;
            }
        }
        else if ((o instanceof Strain) && (this.getName() != null) && (((Strain) o).getName() != null))
        {
            int result = this.getName().compareTo(((Strain) o).getName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }    
*/

}
