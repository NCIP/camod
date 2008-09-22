

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * A transgene is an engineered gene consisting at least of a regulatory element and a coding sequence; 
   * regulatory element and coding sequence can come from different species. 
   * 
   */

public  class Transgene 
    extends EngineeredGene
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public String locationOfIntegration;
	   public String getLocationOfIntegration(){
	      return locationOfIntegration;
	   }
	   public void setLocationOfIntegration(String locationOfIntegration){
	      this.locationOfIntegration = locationOfIntegration;
	   }
	
	   
	   public java.lang.Boolean isRandom;
	   public  java.lang.Boolean getIsRandom(){
	      return isRandom;
	   }
	   public void setIsRandom( java.lang.Boolean isRandom){
	      this.isRandom = isRandom;
	   }
	
	   
	   public java.lang.String constructSequence;
	   public  java.lang.String getConstructSequence(){
	      return constructSequence;
	   }
	   public void setConstructSequence( java.lang.String constructSequence){
	      this.constructSequence = constructSequence;
	   }
	

	
	   
	   
	   
	      
			private java.util.Collection regulatoryElementCollection = new java.util.HashSet();
			public java.util.Collection getRegulatoryElementCollection(){
			try{
			   if(regulatoryElementCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.Transgene thisIdSet = new gov.nih.nci.camod.domain.Transgene();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.RegulatoryElement", thisIdSet);				 
				 	regulatoryElementCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("Transgene:getRegulatoryElementCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return regulatoryElementCollection;
	          }
			   
			   
			   
			   
			   
	      
	               
	   
	   	public void setRegulatoryElementCollection(java.util.Collection regulatoryElementCollection){
	   		this.regulatoryElementCollection = regulatoryElementCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.Species species;
			public gov.nih.nci.camod.domain.Species getSpecies(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.Transgene thisIdSet = new gov.nih.nci.camod.domain.Transgene();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Species", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     species = (gov.nih.nci.camod.domain.Species)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("Transgene:getSpecies throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return species;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setSpecies(gov.nih.nci.camod.domain.Species species){
		this.species = species;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Transgene) {
				Transgene c =(Transgene)obj; 			 
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