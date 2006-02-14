

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
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
	

	
	   
	   
	   
	      

			private java.util.Collection environmentalFactorCollection = new java.util.HashSet();
			public java.util.Collection getEnvironmentalFactorCollection(){
			try{
			   if(environmentalFactorCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.InducedMutation thisIdSet = new gov.nih.nci.camod.domain.InducedMutation();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.EnvironmentalFactor", thisIdSet);				 
				 	environmentalFactorCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("InducedMutation:getEnvironmentalFactorCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return environmentalFactorCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setEnvironmentalFactorCollection(java.util.Collection environmentalFactorCollection){
	   		this.environmentalFactorCollection = environmentalFactorCollection;
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