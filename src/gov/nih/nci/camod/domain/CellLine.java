/**
 * 
 * $Id: CellLine.java,v 1.14 2006-04-17 19:13:46 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.13  2006/01/18 14:23:31  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.12  2005/11/03 16:54:55  piparom
 * implemented "getXXXColectionSorted" method for every getCollection method.
 *
 * Revision 1.11  2005/11/02 19:10:52  georgeda
 * Removed warnings
 *
 * Revision 1.10  2005/11/02 17:24:47  piparom
 * fixed issue in collection fetching..added null checking prior to sorting
 *
 * Revision 1.9  2005/11/01 17:12:23  piparom
 * updates for collection sorts.. implementation of compareTo, and updates to equal/hashcode
 *
 * Revision 1.8  2005/10/21 19:40:21  piparom
 * implementation of Duplicatable interface on deep-copy domain beans
 *
 * Revision 1.7  2005/10/20 20:25:00  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;
import java.io.Serializable;
import java.util.*;

public class CellLine extends BaseObject implements Comparable, Serializable, Duplicatable
{

    private static final long serialVersionUID = 3259655453799404851L;

    private String name;
    private String experiment;
    private String results;
    private String comments;
    private Set<Publication> publicationCollection = new TreeSet<Publication>();
    private Organ organ;
    private AbstractCancerModel cancerModel;


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
     * @return Returns the experiment.
     */
    public String getExperiment()
    {
        return experiment;
    }

    /**
     * @param experiment
     *            The experiment to set.
     */
    public void setExperiment(String experiment)
    {
        this.experiment = experiment;
    }

    /**
     * @return Returns the results.
     */
    public String getResults()
    {
        return results;
    }

    /**
     * @param results
     *            The results to set.
     */
    public void setResults(String results)
    {
        this.results = results;
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
     * @return Returns the organ.
     */
    public Organ getOrgan()
    {
        return organ;
    }

    /**
     * @param organ
     *            The organ to set.
     */
    public void setOrgan(Organ organ)
    {
        this.organ = organ;
    }

    /**
     * @return Returns the publicationCollection.
     */
    public Set getPublicationCollection()
    {
        return publicationCollection;
    }

    /**
     * @param publicationCollection
     *            The publicationCollection to set.
     */
    public void setPublicationCollection(Set<Publication> publicationCollection)
    {
        this.publicationCollection = publicationCollection;
    }

    /**
     * @param publication
     *            The publication to add.
     */
    public void addPublication(Publication publication)
    {
        publicationCollection.add(publication);
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
        final CellLine obj = (CellLine) o;
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
        if ((o instanceof CellLine) && (this.getName() != null) && (((CellLine) o).getName() != null))
        {
            int result = this.getName().compareTo(((CellLine) o).getName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }


}
