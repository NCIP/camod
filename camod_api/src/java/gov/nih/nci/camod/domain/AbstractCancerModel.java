

package gov.nih.nci.camod.domain;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;
  /**
   * Abstract base class for cancer models.
   */

public  class AbstractCancerModel 
	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;

	
	   
	   public String experimentDesign;
	   public String getExperimentDesign(){
	      return experimentDesign;
	   }
	   public void setExperimentDesign(String experimentDesign){
	      this.experimentDesign = experimentDesign;
	   }
	
	   
	   public String modelDescriptor;
	   public String getModelDescriptor(){
	      return modelDescriptor;
	   }
	   public void setModelDescriptor(String modelDescriptor){
	      this.modelDescriptor = modelDescriptor;
	   }
	
	   
	   public Long id;
	   public Long getId(){
	      return id;
	   }
	   public void setId(Long id){
	      this.id = id;
	   }
	
	   
	   public String state;
	   public String getState(){
	      return state;
	   }
	   public void setState(String state){
	      this.state = state;
	   }
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Person principalInvestigator;
			public gov.nih.nci.camod.domain.Person getPrincipalInvestigator(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.AbstractCancerModel thisIdSet = new gov.nih.nci.camod.domain.AbstractCancerModel();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Person", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                principalInvestigator = (gov.nih.nci.camod.domain.Person)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("AbstractCancerModel:getPrincipalInvestigator throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return principalInvestigator;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setPrincipalInvestigator(gov.nih.nci.camod.domain.Person principalInvestigator){
		this.principalInvestigator = principalInvestigator;
	   }	
	   
	   
	
	   
	   
	   
	      

			private java.util.Collection publicationCollection = new java.util.HashSet();
			public java.util.Collection getPublicationCollection(){
			try{
			   if(publicationCollection.size() == 0) {}
		           } catch(Exception e) {			     
			      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			      try {
			      
			      
			         
				 	gov.nih.nci.camod.domain.AbstractCancerModel thisIdSet = new gov.nih.nci.camod.domain.AbstractCancerModel();
			         	thisIdSet.setId(this.getId());
			         	java.util.Collection resultList = applicationService.search("gov.nih.nci.camod.domain.Publication", thisIdSet);				 
				 	publicationCollection = resultList;  
				 	return resultList;
				 
			      
			      }catch(Exception ex) 
			      {
			      	System.out.println("AbstractCancerModel:getPublicationCollection throws exception ... ...");
			   		ex.printStackTrace(); 
			      }
			   }	
	              return publicationCollection;
	          }
			   
			  
			   
	      
	               
	   
	   	public void setPublicationCollection(java.util.Collection publicationCollection){
	   		this.publicationCollection = publicationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.Availability availability;
			public gov.nih.nci.camod.domain.Availability getAvailability(){
			
              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.AbstractCancerModel thisIdSet = new gov.nih.nci.camod.domain.AbstractCancerModel();
			  thisIdSet.setId(this.getId());
			  try {
			  java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Availability", thisIdSet);				 
			 
			  if (resultList!=null && resultList.size()>0) {
			     availability = (gov.nih.nci.camod.domain.Availability)resultList.get(0);
			     }
			  } catch(Exception ex) 
			  { 
			      	System.out.println("AbstractCancerModel:getAvailability throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return availability;			
			 		
              }
                        
	      
	               
	   
	   
	   
	   public void setAvailability(gov.nih.nci.camod.domain.Availability availability){
		this.availability = availability;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Person submitter;
			public gov.nih.nci.camod.domain.Person getSubmitter(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.AbstractCancerModel thisIdSet = new gov.nih.nci.camod.domain.AbstractCancerModel();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Person", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                submitter = (gov.nih.nci.camod.domain.Person)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("AbstractCancerModel:getSubmitter throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return submitter;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setSubmitter(gov.nih.nci.camod.domain.Person submitter){
		this.submitter = submitter;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.Taxon species;
			public gov.nih.nci.camod.domain.Taxon getSpecies(){
			
			
			
			  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();
			  gov.nih.nci.camod.domain.AbstractCancerModel thisIdSet = new gov.nih.nci.camod.domain.AbstractCancerModel();
			  thisIdSet.setId(this.getId());
			  
			  try {
			     java.util.List resultList = applicationService.search("gov.nih.nci.camod.domain.Taxon", thisIdSet);				 
		             if (resultList!=null && resultList.size()>0) {
		                species = (gov.nih.nci.camod.domain.Taxon)resultList.get(0);
		             }
		          
			  } catch(Exception ex) 
			  { 
			      	System.out.println("AbstractCancerModel:getSpecies throws exception ... ...");
			   		ex.printStackTrace(); 
			  }
			  return species;	
			 
			 		
           }
		   
	      
	               
	   
	   
	   
	   public void setSpecies(gov.nih.nci.camod.domain.Taxon species){
		this.species = species;
	   }	
	   
	   
	

  
		public boolean equals(Object obj){
			boolean eq = false;
			if(obj instanceof AbstractCancerModel) {
				AbstractCancerModel c =(AbstractCancerModel)obj; 			 
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