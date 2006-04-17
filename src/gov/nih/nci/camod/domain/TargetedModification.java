/*
 * $Id: TargetedModification.java,v 1.14 2006-04-17 19:13:46 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.domain;

import java.util.*;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TargetedModification extends EngineeredGene
{

    private static final long serialVersionUID = 3258565453799404851L;

    private String esCellLineName;
    private String blastocystName;
    private String geneId;
    private String constructSequence;   
    private Set<ModificationType> modificationTypeCollection = new TreeSet<ModificationType>();
    


    /**
     * @return Returns the esCellLineName.
     */
    public String getEsCellLineName()
    {
        return esCellLineName;
    }

    /**
     * @param esCellLineName
     *            The esCellLineName to set.
     */
    public void setEsCellLineName(String esCellLineName)
    {
        this.esCellLineName = esCellLineName;
    }
    /**
     * @return Returns the blastocystName.
     */
    public String getBlastocystName()
    {
        return blastocystName;
    }

    /**
     * @param blastocystName
     *            The blastocystName to set.
     */
    public void setBlastocystName(String blastocystName)
    {
        this.blastocystName = blastocystName;
    }    
    /**
     * @return Returns the modificationTypeCollection.
     */
    public Set<ModificationType> getModificationTypeCollection()
    {
        return modificationTypeCollection;
    }

    /**
     * @param modificationTypeCollection
     *            The modificationTypeCollection to set.
     */
    public void setModificationTypeCollection(Set<ModificationType> modificationTypeCollection)
    {
        this.modificationTypeCollection = modificationTypeCollection;
    }

    /**  Sima TODO - do we need this?
     * @param modificationType
     *            The modificationType to add.
     
    public void addModificationType(ModificationType modificationType)
    {
        modificationType.getTargetedModificationCollection().add(this);
        modificationTypeCollection.add(modificationType);
    }
    */
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
     * @return Returns the constructSequence.
     */
    public String getConstructSequence()
    {
        return constructSequence;
    }

    /**
     * @param constructSequence
     *            The constructSequence to set.
     */
    public void setConstructSequence(String constructSequence)
    {
        this.constructSequence = constructSequence;
    }    
    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getGeneId();
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
}
