

package gov.nih.nci.camod.domain.ws;
import gov.nih.nci.camod.domain.ws.*;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.system.applicationservice.*;
import java.util.*;


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
	

	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.PersonImpl principalInvestigator;
			public gov.nih.nci.camod.domain.ws.PersonImpl getPrincipalInvestigator(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setPrincipalInvestigator(gov.nih.nci.camod.domain.ws.PersonImpl principalInvestigator){
		this.principalInvestigator = principalInvestigator;
	   }	
	   
	   
	
	   
	   
	   
	      
			private java.util.Collection publicationCollection = new java.util.HashSet();
			public java.util.Collection getPublicationCollection(){
	              return publicationCollection;
	          }
			   
			   
			   
			   			   
	      
	               
	   
	   	public void setPublicationCollection(java.util.Collection publicationCollection){
	   		this.publicationCollection = publicationCollection;
	        }	
	   
	   
	
	   
	   
	   
	      
	   
	
	   
	   
	   
	      
			
			
			private gov.nih.nci.camod.domain.ws.AvailabilityImpl availability;
			public gov.nih.nci.camod.domain.ws.AvailabilityImpl getAvailability(){
			  return availability;			
                        }
                        
	      
	               
	   
	   
	   
	   public void setAvailability(gov.nih.nci.camod.domain.ws.AvailabilityImpl availability){
		this.availability = availability;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.PersonImpl submitter;
			public gov.nih.nci.camod.domain.ws.PersonImpl getSubmitter(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setSubmitter(gov.nih.nci.camod.domain.ws.PersonImpl submitter){
		this.submitter = submitter;
	   }	
	   
	   
	
	   
	   
	   
	      
			
			
			
			
			private gov.nih.nci.camod.domain.ws.TaxonImpl species;
			public gov.nih.nci.camod.domain.ws.TaxonImpl getSpecies(){
			  return null;			
                        }
		   
	      
	               
	   
	   
	   
	   public void setSpecies(gov.nih.nci.camod.domain.ws.TaxonImpl species){
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
