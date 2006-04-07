

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * Disease process or gene that the agent is targeted at.
   */

public  class AgentTarget 
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
	

	
	   
	   
	   
	      
			private java.util.Collection agentCollection = new java.util.HashSet();
			public java.util.Collection getAgentCollection(){
			try{
			   if(agentCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.AgentTarget thisIdSet = new gov.nih.nci.camod.domain.AgentTarget();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Agent", thisIdSet);				 
				 	agentCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("AgentTarget:getAgentCollection throws exception ... ...");
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
			if(obj instanceof AgentTarget) {
				AgentTarget c =(AgentTarget)obj; 			 
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