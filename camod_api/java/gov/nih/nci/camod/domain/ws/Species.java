

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */

public  class Species 
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public java.lang.Long id;
	   public  java.lang.Long getId(){
	      return id;
	   }
	   
	   public void setId( java.lang.Long id){
	      this.id = id;
	   }
	
	   
	   public String scientificName;
	   public String getScientificName(){
	      return scientificName;
	   }
	   
	   public void setScientificName(String scientificName){
	      this.scientificName = scientificName;
	   }
	
	   
	   public java.lang.String scientificNameUnctrlVocab;
	   public  java.lang.String getScientificNameUnctrlVocab(){
	      return scientificNameUnctrlVocab;
	   }
	   
	   public void setScientificNameUnctrlVocab( java.lang.String scientificNameUnctrlVocab){
	      this.scientificNameUnctrlVocab = scientificNameUnctrlVocab;
	   }
	
	   
	   public String commonName;
	   public String getCommonName(){
	      return commonName;
	   }
	   
	   public void setCommonName(String commonName){
	      this.commonName = commonName;
	   }
	
	   
	   public java.lang.String commonNameUnctrlVocab;
	   public  java.lang.String getCommonNameUnctrlVocab(){
	      return commonNameUnctrlVocab;
	   }
	   
	   public void setCommonNameUnctrlVocab( java.lang.String commonNameUnctrlVocab){
	      this.commonNameUnctrlVocab = commonNameUnctrlVocab;
	   }
	
	   
	   public String abbreviation;
	   public String getAbbreviation(){
	      return abbreviation;
	   }
	   
	   public void setAbbreviation(String abbreviation){
	      this.abbreviation = abbreviation;
	   }
	
	   
	   public java.lang.String conceptCode;
	   public  java.lang.String getConceptCode(){
	      return conceptCode;
	   }
	   
	   public void setConceptCode( java.lang.String conceptCode){
	      this.conceptCode = conceptCode;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection strainCollection = new java.util.HashSet();
			public java.util.Collection getStrainCollection(){
	              return strainCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setStrainCollection(java.util.Collection strainCollection){
	   		this.strainCollection = strainCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Species) {
				Species c =(Species)obj; 			 
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
