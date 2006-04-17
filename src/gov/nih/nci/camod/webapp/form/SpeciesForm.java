/**
 * 
 * $Id: SpeciesForm.java,v 1.1 2006-04-17 19:09:19 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

public class SpeciesForm extends BaseForm implements Serializable, SpeciesData 
{
    private static final long serialVersionUID = 3257225453799404851L;
    
    /**
     * Default empty constructor
     * @author pandyas
     */
    public SpeciesForm() {}
    
    protected String scientificName;
    protected String commonName;

    /**
     * @return Returns the scientificName.
     */ 
    public String getScientificName() {
        return scientificName;
    }
    /**
     * @param scientificName The scientificName to set.
     */ 
    public void setScientificName( String scientificName ) {
        this.scientificName = scientificName;
    }
    /**
     * @return Returns the commonName.
     */ 
    public String getCommonName() {
        return commonName;
    }
    /**
     * @param commonName The commonName to set.
     */ 
    public void setCommonName( String commonName ) {
        this.commonName = commonName;
    }
    /**
     * @return Returns the scientificName + commonName.
     */ 
    public String getSpeciesDisplayName() {
        String name = scientificName+" ("+commonName+")";
        return name;
    }    
   
}
