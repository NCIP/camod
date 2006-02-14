

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * Functions of the engineered genes.
   */

public  class GeneFunction 
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
	
	   
	   public String function;
	   public String getFunction(){
	      return function;
	   }
	   public void setFunction(String function){
	      this.function = function;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.EngineeredGene engineeredGene;
			public gov.nih.nci.camod.domain.EngineeredGene getEngineeredGene(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.GeneFunction thisIdSet = new gov.nih.nci.camod.domain.GeneFunction();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.EngineeredGene", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                engineeredGene = (gov.nih.nci.camod.domain.EngineeredGene)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("GeneFunction:getEngineeredGene throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return engineeredGene;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setEngineeredGene(gov.nih.nci.camod.domain.EngineeredGene engineeredGene){
		this.engineeredGene = engineeredGene;
	   }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof GeneFunction) {
				GeneFunction c =(GeneFunction)obj; 			 
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