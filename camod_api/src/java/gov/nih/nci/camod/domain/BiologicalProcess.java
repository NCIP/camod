

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * A Biological Process is an activity that occurs between organisms or that occurs within an organism 
   * and involves the function, or modification of function by external factors, of biologic molecules, 
   * biologic complexes, subcellular components, cells, tissues, organs, or organ systems. 
   * 
   */

public  class BiologicalProcess 
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
	
	   
	   public String processName;
	   public String getProcessName(){
	      return processName;
	   }
	   public void setProcessName(String processName){
	      this.processName = processName;
	   }
	

	
	   
	   
	   
	      

			private java.util.Collection agentCollection = new java.util.HashSet();
			public java.util.Collection getAgentCollection(){
			try{
			   if(agentCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.BiologicalProcess thisIdSet = new gov.nih.nci.camod.domain.BiologicalProcess();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Agent", thisIdSet);				 
				 	agentCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("BiologicalProcess:getAgentCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return agentCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setAgentCollection(java.util.Collection agentCollection){
	   		this.agentCollection = agentCollection;
	        }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof BiologicalProcess) {
				BiologicalProcess c =(BiologicalProcess)obj; 			 
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