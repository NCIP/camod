/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 *
 * $Id: DevelopmentalStage.java,v 1.2 2009-05-20 17:07:20 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2007/05/16 12:28:23  pandyas
 * Added developmental stage evs tree to Therapy when species is Zebrafsih
 *
 * 
 */

package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.camod.util.HashCodeUtil;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author pandyas
 */
public class DevelopmentalStage extends BaseObject implements Comparable, Serializable, Duplicatable{

    private static final long serialVersionUID = 3259095453799404851L;

    private String name;
    private String conceptCode;
    private Set<Therapy> therapyCollection = new HashSet<Therapy>();

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
     * @return Returns the conceptCode.
     */
    public String getConceptCode()
    {
        return conceptCode;
    }

    /**
     * @param conceptCode
     *            The conceptCode to set.
     */
    public void setConceptCode(String conceptCode)
    {
        this.conceptCode = conceptCode;
    }

    /**
     * @return Returns the therapyCollection.
     */
    public Set<Therapy> getTherapyCollection()
    {
        return therapyCollection;
    }

    /**
     * @param therapyCollection
     *            The therapyCollection to set.
     */
    public void setTherapyCollection(Set<Therapy> therapyCollection)
    {
        this.therapyCollection = therapyCollection;
    }

    public void addTherapy(Therapy therapy)
    {
    	therapyCollection.add(therapy);
    }
    
    /**
     * @return Returns the EVS Preferred displayName
    
    public String getEVSPreferredDescription()
    {
      String thePreferedDesc = null;
      
      String conceptDetails = EvsTreeUtil.getConceptDetails(null, conceptCode);

      	if(conceptDetails != null & conceptDetails.length() > 0){
       		thePreferedDesc = conceptDetails;
       		System.out.println("Developmental Stage retrieved from EVS: " + thePreferedDesc);
       	} else {       		
       		thePreferedDesc = name;
       		System.out.println("Developmental Stage retrieved from caMOD: " + thePreferedDesc);
       	}
        return thePreferedDesc;        
    }    
     */
    /**
     * @see java.lang.Object#toString()
     */
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
        final DevelopmentalStage obj = (DevelopmentalStage) o;
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

    public int compareTo(Object o)
    {
        // compare by evs concept code
        if ((o instanceof DevelopmentalStage) && (this.conceptCode != null) && (((DevelopmentalStage) o).conceptCode != null))
        {
            int result = this.conceptCode.compareTo(((DevelopmentalStage) o).conceptCode);
            if (result != 0)
            {
                return result;
            }
        }
        else if ((o instanceof DevelopmentalStage) && (this.getName() != null) && (((DevelopmentalStage) o).getName() != null))
        {
            int result = this.getName().compareTo(((DevelopmentalStage) o).getName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }	
}
