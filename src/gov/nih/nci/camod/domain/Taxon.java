/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;


/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Taxon extends BaseObject implements Serializable {
	
	private static final long serialVersionUID = 3258545453799404851L;	
		
	private String scientificName;
	private String ethnicityStrain;
	private String ethnicityStrainUnctrlVocab;	
	private String abbreviation;
	private String commonName;
	
	/**
	 * @return Returns the abbreviation.
	 */
	public String getAbbreviation() {
		return abbreviation;
	}
	/**
	 * @param abbreviation The abbreviation to set.
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
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
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	/**
	 * @return Returns the ethnicityStrain.
	 */
	public String getEthnicityStrain() {
		return ethnicityStrain;
	}
	/**
	 * @param ethnicityStrain The ethnicityStrain to set.
	 */
	public void setEthnicityStrain(String ethnicityStrain) {
		this.ethnicityStrain = ethnicityStrain;
	}
	/**
	 * @return Returns the ethnicityStrainUnctrlVocab.
	 */
	public String getEthnicityStrainUnctrlVocab() {
		return ethnicityStrainUnctrlVocab;
	}
	/**
	 * @param ethnicityStrainUnctrlVocab The ethnicityStrainUnctrlVocab to set.
	 */
	public void setEthnicityStrainUnctrlVocab(String ethnicityStrainUnctrlVocab) {
		this.ethnicityStrainUnctrlVocab = ethnicityStrainUnctrlVocab;
	}	

	/**
	 * @return Returns the scientificName.
	 */
	public String getScientificName() {
		return scientificName;
	}
	/**
	 * @param scientificName The scientificName to set.
	 */
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}
   /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getScientificName()+" - "+this.getCommonName();
       return result;
     }  
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
}
