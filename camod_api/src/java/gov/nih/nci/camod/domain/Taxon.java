

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * An object representing the various names (scientific, common, abbreviated, etc.) for a species 
   * associated with a specific instance of a Gene, Chromosome, Pathway, Protein, or Tissue. 
   * 
   */

public  class Taxon 
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public Long id;
	   public Long getId(){
	      return id;
	   }
	   public void setId(Long id){
	      this.id = id;
	   }
	
	   
	   public String scientificName;
	   public String getScientificName(){
	      return scientificName;
	   }
	   public void setScientificName(String scientificName){
	      this.scientificName = scientificName;
	   }
	
	   
	   public String ethnicityStrain;
	   public String getEthnicityStrain(){
	      return ethnicityStrain;
	   }
	   public void setEthnicityStrain(String ethnicityStrain){
	      this.ethnicityStrain = ethnicityStrain;
	   }
	
	   
	   public String abbreviation;
	   public String getAbbreviation(){
	      return abbreviation;
	   }
	   public void setAbbreviation(String abbreviation){
	      this.abbreviation = abbreviation;
	   }
	
	   
	   public String commonName;
	   public String getCommonName(){
	      return commonName;
	   }
	   public void setCommonName(String commonName){
	      this.commonName = commonName;
	   }
	
	   
	   public String ethnicityStrainUnctrlVocab;
	   public String getEthnicityStrainUnctrlVocab(){
	      return ethnicityStrainUnctrlVocab;
	   }
	   public void setEthnicityStrainUnctrlVocab(String ethnicityStrainUnctrlVocab){
	      this.ethnicityStrainUnctrlVocab = ethnicityStrainUnctrlVocab;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Taxon) {
				Taxon c =(Taxon)obj; 			 
				Long thisId = getId();		
				
					if(thisId != null && thisId.equals(c.getId())) {
					   eq = true;
				    }		
				
			}
			return eq;
		}
 	
		public int hashCode(){
			int h = 0;
			
			if(getId() != null) {
				h += getId().hashCode();
			}
			
			return h;
	}
	
	   
			   
	
}