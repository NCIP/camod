/*
 * Created on August 9, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.List;

import gov.nih.nci.camod.util.HashCodeUtil;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ModificationType extends BaseObject implements Comparable, Serializable
{

    private static final long serialVersionUID = 3259155453799404851L;

    private String name;
    private List<TargetedModification> targetedModificationCollection = new ArrayList<TargetedModification>();

    /**
     * @return Returns the targetedModificationCollection.
     */
    public List<TargetedModification> getTargetedModificationCollection()
    {
        return targetedModificationCollection;
    }

    public List<TargetedModification> getTargetedModificationCollectionSorted()
    {
        if (targetedModificationCollection != null)
            return new ArrayList<TargetedModification>(new TreeSet<TargetedModification>(targetedModificationCollection));
        return null;
    }

    /**
     * @param targetedModificationCollection
     *            The targetedModificationCollection to set.
     */
    public void setTargetedModificationCollection(List<TargetedModification> targetedModificationCollection)
    {
        this.targetedModificationCollection = targetedModificationCollection;
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
        final ModificationType obj = (ModificationType) o;
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
        if ((o instanceof ModificationType) && (this.getName() != null) && (((ModificationType) o).getName() != null))
        {
            int result = this.getName().compareTo(((ModificationType) o).getName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }

}
