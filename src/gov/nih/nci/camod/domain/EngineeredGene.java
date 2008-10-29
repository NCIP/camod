/*
 * $Id: EngineeredGene.java,v 1.17 2008-10-29 18:54:56 schroedn Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.16  2008/10/16 13:54:13  schroedn
 * Added absCancerModelId, environmentalFactorId and engineeredGeneType. Used for HQL searching
 *
 * Revision 1.15  2006/10/17 16:14:36  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.14  2006/04/18 16:26:52  pandyas
 * Removed getGeneFunctionCollectionSorted() method since it is taken care of in the mapping
 *
 * Revision 1.13  2006/04/17 19:13:46  pandyas
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
public class EngineeredGene extends BaseObject implements Comparable, Serializable, Duplicatable
{

    private static final long serialVersionUID = 3259475453799404851L;

    private String name;
    private String comments;
    private Image image;
    private long absCancerModelId;
    //private long environmentalFactorId;
    private String engineeredGeneType;
    private Conditionality conditionality;
    private MutationIdentifier mutationIdentifier;
    private AbstractCancerModel cancerModel;    
    private Set<ExpressionFeature> expressionFeatureCollection = new TreeSet<ExpressionFeature>();
    private Set<GeneFunction> geneFunctionCollection = new TreeSet<GeneFunction>();

    /**
     * @return Returns the geneFunctionCollection.
     */
    public Set<GeneFunction> getGeneFunctionCollection()
    {
        return geneFunctionCollection;
    }

    /**
     * @param geneFunctionCollection
     *            The geneFunctionCollection to set.
     */
    public void setGeneFunctionCollection(Set<GeneFunction> geneFunctionCollection)
    {
        this.geneFunctionCollection = geneFunctionCollection;
    }

    /**
     * @param geneFunction
     *            The geneFunction to add.
     */
    public void addGeneFunction(GeneFunction geneFunction)
    {
        geneFunctionCollection.add(geneFunction);
    }

    /**
     * @return Returns the expressionFeatureCollection.
     */
    public Set<ExpressionFeature> getExpressionFeatureCollection()
    {
        return expressionFeatureCollection;
    }

    /**
     * @param expressionFeatureCollection
     *            The expressionFeatureCollection to set.
     */
    public void setExpressionFeatureCollection(Set<ExpressionFeature> expressionFeatureCollection)
    {
        this.expressionFeatureCollection = expressionFeatureCollection;
    }

    /**
     * @param expressionFeature
     *            The expressionFeature to add.
     */
    public void addExpressionFeature(ExpressionFeature expressionFeature)
    {
        expressionFeatureCollection.add(expressionFeature);
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
     * @return Returns the comments.  Comment is a reserved word so 
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
     * @return Returns the conditionality.
     */
    public Conditionality getConditionality()
    {
        return conditionality;
    }

    /**
     * @param conditionality
     *            The conditionality to set.
     */
    public void setConditionality(Conditionality conditionality)
    {
        this.conditionality = conditionality;
    }

    /**
     * @return Returns the image.
     */
    public Image getImage()
    {
        return image;
    }

    /**
     * @param image
     *            The image to set.
     */
    public void setImage(Image image)
    {
        this.image = image;
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
        final EngineeredGene obj = (EngineeredGene) o;
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
        if ((o instanceof EngineeredGene) && (this.getName() != null) && (((EngineeredGene) o).getName() != null))
        {
            int result = this.getName().compareTo(((EngineeredGene) o).getName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }

	/**
	 * @return the absCancerModelId
	 */
	public long getAbsCancerModelId() {
		return absCancerModelId;
	}

	/**
	 * @param absCancerModelId the absCancerModelId to set
	 */
	public void setAbsCancerModelId(long absCancerModelId) {
		this.absCancerModelId = absCancerModelId;
	}

	/**
	 * @return the engineeredGeneType
	 */
	public String getEngineeredGeneType() {
		return engineeredGeneType;
	}

	/**
	 * @param engineeredGeneType the engineeredGeneType to set
	 */
	public void setEngineeredGeneType(String engineeredGeneType) {
		this.engineeredGeneType = engineeredGeneType;
	}

//	/**
//	 * @return the environmentalFactorId
//	 */
//	public long getEnvironmentalFactorId() {
//		return environmentalFactorId;
//	}
//
//	/**
//	 * @param environmentalFactorId the environmentalFactorId to set
//	 */
//	public void setEnvironmentalFactorId(long environmentalFactorId) {
//			this.environmentalFactorId = environmentalFactorId;
//	}
}
