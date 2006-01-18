/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.*;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class EngineeredGene extends BaseObject implements Comparable, Serializable, Duplicatable
{

    private static final long serialVersionUID = 3259475453799404851L;

    private Long cabioId;
    private String name;
    private String comments;
    private GenotypeSummary genotypeSummary;
    private Image image;
    private Conditionality conditionality;
    private MutationIdentifier mutationIdentifier;
    private List<ExpressionFeature> expressionFeatureCollection = new ArrayList<ExpressionFeature>();
    private Set<GeneFunction> geneFunctionCollection = new HashSet<GeneFunction>();

    /**
     * @return Returns the geneFunctionCollection.
     */
    public Set<GeneFunction> getGeneFunctionCollection()
    {
        return geneFunctionCollection;
    }

    public List<GeneFunction> getGeneFunctionCollectionSorted()
    {
        if (geneFunctionCollection != null)
            return new ArrayList<GeneFunction>(new TreeSet<GeneFunction>(geneFunctionCollection));
        return null;
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
    public List<ExpressionFeature> getExpressionFeatureCollection()
    {
        return expressionFeatureCollection;
    }

    public List<ExpressionFeature> getExpressionFeatureCollectionSorted()
    {
        if (expressionFeatureCollection != null)
            return new ArrayList<ExpressionFeature>(new TreeSet<ExpressionFeature>(expressionFeatureCollection));
        return null;
    }

    /**
     * @param expressionFeatureCollection
     *            The expressionFeatureCollection to set.
     */
    public void setExpressionFeatureCollection(List<ExpressionFeature> expressionFeatureCollection)
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
     * @return Returns the comments.
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
     * @return Returns the genotypeSummary.
     */
    public GenotypeSummary getGenotypeSummary()
    {
        return genotypeSummary;
    }

    /**
     * @param genotypeSummary
     *            The genotypeSummary to set.
     */
    public void setGenotypeSummary(GenotypeSummary genotypeSummary)
    {
        this.genotypeSummary = genotypeSummary;
    }

    /**
     * @return Returns the cabioId.
     */
    public Long getCabioId()
    {
        return cabioId;
    }

    /**
     * @param cabioId
     *            The cabioId to set.
     */
    public void setCabioId(Long cabioId)
    {
        this.cabioId = cabioId;
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

}
