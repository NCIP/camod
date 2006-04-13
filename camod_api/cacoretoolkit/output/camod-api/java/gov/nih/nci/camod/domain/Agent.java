

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * A therapeutic agent (drug, intervention therapy) used in a clinical trail protocol. 
   * 
   */

public  class Agent 
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
	
	   
	   public java.lang.String name;
	   public  java.lang.String getName(){
	      return name;
	   }
	   public void setName( java.lang.String name){
	      this.name = name;
	   }
	
	   
	   public String conceptCode;
	   public String getConceptCode(){
	      return conceptCode;
	   }
	   public void setConceptCode(String conceptCode){
	      this.conceptCode = conceptCode;
	   }
	
	   
	   public Long nscNumber;
	   public Long getNscNumber(){
	      return nscNumber;
	   }
	   public void setNscNumber(Long nscNumber){
	      this.nscNumber = nscNumber;
	   }
	
	   
	   public java.lang.String casNumber;
	   public  java.lang.String getCasNumber(){
	      return casNumber;
	   }
	   public void setCasNumber( java.lang.String casNumber){
	      this.casNumber = casNumber;
	   }
	
	   
	   public Boolean isCMAPAgent;
	   public Boolean getIsCMAPAgent(){
	      return isCMAPAgent;
	   }
	   public void setIsCMAPAgent(Boolean isCMAPAgent){
	      this.isCMAPAgent = isCMAPAgent;
	   }
	
	   
	   public String source;
	   public String getSource(){
	      return source;
	   }
	   public void setSource(String source){
	      this.source = source;
	   }
	
	   
	   public String comments;
	   public String getComments(){
	      return comments;
	   }
	   public void setComments(String comments){
	      this.comments = comments;
	   }
	

	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection chemicalClassCollection = new java.util.HashSet();
			public java.util.Collection getChemicalClassCollection(){
			try{
			   if(chemicalClassCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.Agent thisIdSet = new gov.nih.nci.camod.domain.Agent();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.ChemicalClass", thisIdSet);				 
				 	chemicalClassCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("Agent:getChemicalClassCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return chemicalClassCollection;
	          }
			   
			   
			   
			   
			   
	      
	               
	   
	   	public void setChemicalClassCollection(java.util.Collection chemicalClassCollection){
	   		this.chemicalClassCollection = chemicalClassCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			private java.util.Collection biologicalProcessCollection = new java.util.HashSet();
			public java.util.Collection getBiologicalProcessCollection(){
			try{
			   if(biologicalProcessCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.Agent thisIdSet = new gov.nih.nci.camod.domain.Agent();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.BiologicalProcess", thisIdSet);				 
				 	biologicalProcessCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("Agent:getBiologicalProcessCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return biologicalProcessCollection;
	          }
			   
			   
			   
			   
			   
	      
	               
	   
	   	public void setBiologicalProcessCollection(java.util.Collection biologicalProcessCollection){
	   		this.biologicalProcessCollection = biologicalProcessCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection agentTargetCollection = new java.util.HashSet();
			public java.util.Collection getAgentTargetCollection(){
			try{
			   if(agentTargetCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.Agent thisIdSet = new gov.nih.nci.camod.domain.Agent();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.AgentTarget", thisIdSet);				 
				 	agentTargetCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("Agent:getAgentTargetCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return agentTargetCollection;
	          }
			   
			   
			   
			   
			   
	      
	               
	   
	   	public void setAgentTargetCollection(java.util.Collection agentTargetCollection){
	   		this.agentTargetCollection = agentTargetCollection;
	        }	
	   
	   
	

		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof Agent) {
				Agent c =(Agent)obj; 			 
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