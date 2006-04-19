/*
 * $Id: SpontaneousMutation.java,v 1.9 2006-04-19 17:37:37 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.*;
import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

/**
 * @author rajputs
 */
public class SpontaneousMutation extends BaseObject implements Comparable, Serializable, Duplicatable
{
    private static final long serialVersionUID = 3258605453799404851L;

    private AbstractCancerModel cancerModel;    
    private String name;
    private String geneId;    
    private String comments;
    private Set<GeneticAlteration> geneticAlterationCollection = new TreeSet<GeneticAlteration>();
    private MutationIdentifier mutationIdentifier;


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
     * @return Returns the geneId.
     */
    public String getGeneId()
    {
        return geneId;
    }

    /**
     * @param geneId
     *            The geneId to set.
     */
    public void setGeneId(String geneId)
    {
        this.geneId = geneId;
    }    
    /**
     * @return Returns the comments. Comment is a reserved word so 
     * we must use the plural form comments.
     */
    public String getComments()
    {
        return comments;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments)
    {
        this.comments = comments;
    }

    /**
     * @return Returns the geneticAlterationCollection.
     */
    public Set getGeneticAlterationCollection()
    {
        return geneticAlterationCollection;
    }

    /**
     * @param geneticAlterationCollection
     *            The geneticAlterationCollection to set.
     */
    public void setGeneticAlterationCollection(Set<GeneticAlteration> geneticAlterationCollection)
    {
        this.geneticAlterationCollection = geneticAlterationCollection;
    }

    /**
     * @param geneticAlteration
     *            The geneticAlteration to add.
     */
    public void addGeneticAlteration(GeneticAlteration geneticAlteration)
    {
        geneticAlterationCollection.add(geneticAlteration);
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
        final SpontaneousMutation obj = (SpontaneousMutation) o;
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
        if ((o instanceof SpontaneousMutation) && (this.getName() != null) && (((SpontaneousMutation) o).getName() != null))
        {
            int result = this.getName().compareTo(((SpontaneousMutation) o).getName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }
}
