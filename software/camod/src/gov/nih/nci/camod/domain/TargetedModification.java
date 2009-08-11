/*
 * $Id: TargetedModification.java,v 1.16 2007-10-31 16:10:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.15  2006/04/19 17:37:37  pandyas
 * Removed TODO text
 *
 * Revision 1.14  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.util.*;

/**
 * @author rajputs
 */
public class TargetedModification extends EngineeredGene
{

    private static final long serialVersionUID = 3258565453799404851L;

    private String esCellLineName;
    private String blastocystName;
    private GeneIdentifier geneIdentifier;    
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

    /**  
     * @param modificationType
     *            The modificationType to add.
     */
    public void addModificationType(ModificationType modificationType)
    {
        modificationTypeCollection.add(modificationType);
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
        result += this.getGeneIdentifier();
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

	/**
	 * @return the geneIdentifier
	 */
	public GeneIdentifier getGeneIdentifier() {
		return geneIdentifier;
	}

	/**
	 * @param geneIdentifier the geneIdentifier to set
	 */
	public void setGeneIdentifier(GeneIdentifier geneIdentifier) {
		this.geneIdentifier = geneIdentifier;
	}
}
