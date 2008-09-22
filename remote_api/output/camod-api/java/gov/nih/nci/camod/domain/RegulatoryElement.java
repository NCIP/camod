

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * A regulatory element controls the expression of a gene and/or and engineered gene. 
   * 
   */

public  class RegulatoryElement 
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
	
	   
	   public String name;
	   public String getName(){
	      return name;
	   }
	   public void setName(String name){
	      this.name = name;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.RegulatoryElementType regulatoryElementType;
			public gov.nih.nci.camod.domain.RegulatoryElementType getRegulatoryElementType(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.RegulatoryElement thisIdSet = new gov.nih.nci.camod.domain.RegulatoryElement();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.RegulatoryElementType", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                regulatoryElementType = (gov.nih.nci.camod.domain.RegulatoryElementType)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("RegulatoryElement:getRegulatoryElementType throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return regulatoryElementType;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setRegulatoryElementType(gov.nih.nci.camod.domain.RegulatoryElementType regulatoryElementType){
		this.regulatoryElementType = regulatoryElementType;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Species species;
			public gov.nih.nci.camod.domain.Species getSpecies(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.RegulatoryElement thisIdSet = new gov.nih.nci.camod.domain.RegulatoryElement();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Species", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                species = (gov.nih.nci.camod.domain.Species)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("RegulatoryElement:getSpecies throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return species;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setSpecies(gov.nih.nci.camod.domain.Species species){
		this.species = species;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof RegulatoryElement) {
				RegulatoryElement c =(RegulatoryElement)obj; 			 
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