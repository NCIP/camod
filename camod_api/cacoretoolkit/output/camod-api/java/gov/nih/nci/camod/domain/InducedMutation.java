

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
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection geneticAlterationCollection = new java.util.HashSet();
			public java.util.Collection getGeneticAlterationCollection(){
			try{
			   if(geneticAlterationCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.InducedMutation thisIdSet = new gov.nih.nci.camod.domain.InducedMutation();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.GeneticAlteration", thisIdSet);				 
				 	geneticAlterationCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("InducedMutation:getGeneticAlterationCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return geneticAlterationCollection;
	          }
			   
			   
			   
			   
			   
	      
	               
	   
	   	public void setGeneticAlterationCollection(java.util.Collection geneticAlterationCollection){
	   		this.geneticAlterationCollection = geneticAlterationCollection;
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