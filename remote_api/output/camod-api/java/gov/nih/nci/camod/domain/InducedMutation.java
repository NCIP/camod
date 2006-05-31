

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Induced mutations are defined as mutations triggered by radiation, chemicals or other means. Progeny 
   * of the treated animal inherits the mutation. 
   * 
   */

public  class InducedMutation 
    extends EngineeredGene
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public String geneId;
	   public String getGeneId(){
	      return geneId;
	   }
	   public void setGeneId(String geneId){
	      this.geneId = geneId;
	   }
	
	   
	   public String description;
	   public String getDescription(){
	      return description;
	   }
	   public void setDescription(String description){
	      this.description = description;
	   }
	

	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.GeneticAlteration geneticAlteration;
			public gov.nih.nci.camod.domain.GeneticAlteration getGeneticAlteration(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.InducedMutation thisIdSet = new gov.nih.nci.camod.domain.InducedMutation();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.GeneticAlteration", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     geneticAlteration = (gov.nih.nci.camod.domain.GeneticAlteration)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("InducedMutation:getGeneticAlteration throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return geneticAlteration;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setGeneticAlteration(gov.nih.nci.camod.domain.GeneticAlteration geneticAlteration){
		this.geneticAlteration = geneticAlteration;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.EnvironmentalFactor environmentalFactor;
			public gov.nih.nci.camod.domain.EnvironmentalFactor getEnvironmentalFactor(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.InducedMutation thisIdSet = new gov.nih.nci.camod.domain.InducedMutation();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.EnvironmentalFactor", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                environmentalFactor = (gov.nih.nci.camod.domain.EnvironmentalFactor)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("InducedMutation:getEnvironmentalFactor throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return environmentalFactor;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setEnvironmentalFactor(gov.nih.nci.camod.domain.EnvironmentalFactor environmentalFactor){
		this.environmentalFactor = environmentalFactor;
	   }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof InducedMutation) {
				InducedMutation c =(InducedMutation)obj; 			 
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